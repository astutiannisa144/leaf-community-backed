package com.lawencon.leaf.community.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.constant.EnumRole;
import com.lawencon.leaf.community.dao.IndustryDao;
import com.lawencon.leaf.community.dao.JobDao;
import com.lawencon.leaf.community.dao.PositionDao;
import com.lawencon.leaf.community.dao.ProfileSocialMediaDao;
import com.lawencon.leaf.community.dao.RoleDao;
import com.lawencon.leaf.community.dao.SocialMediaDao;
import com.lawencon.leaf.community.dao.UserDao;
import com.lawencon.leaf.community.dao.VerificationDao;
import com.lawencon.leaf.community.model.Industry;
import com.lawencon.leaf.community.model.Job;
import com.lawencon.leaf.community.model.Position;
import com.lawencon.leaf.community.model.Profile;
import com.lawencon.leaf.community.model.ProfileSocialMedia;
import com.lawencon.leaf.community.model.Role;
import com.lawencon.leaf.community.model.SocialMedia;
import com.lawencon.leaf.community.model.User;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.user.PojoUserReq;

@Service
public class UserService extends AbstractJpaDao implements UserDetailsService {

	private final UserDao userDao;
	private final RoleDao roleDao;
	private final PasswordEncoder encoder;
	private final EmailSenderService emailSenderService;
	private final IndustryDao industryDao;
	private final PositionDao positionDao;
	private final JobDao jobDao;
	@Autowired
	private VerificationDao verificationDao;
	private final ProfileSocialMediaDao profileSocialMediaDao;
	@Autowired
	private SocialMediaDao socialMediaDao;

	public UserService(UserDao userDao, RoleDao roleDao, PasswordEncoder encoder, EmailSenderService emailSenderService,
			IndustryDao industryDao, PositionDao positionDao, JobDao jobDao,
			ProfileSocialMediaDao profileSocialMediaDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.encoder = encoder;
		this.emailSenderService = emailSenderService;
		this.industryDao = industryDao;
		this.positionDao = positionDao;
		this.jobDao = jobDao;
	
		this.profileSocialMediaDao = profileSocialMediaDao;
	}

	public Optional<User> login(String email) {
		return userDao.getEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Optional<User> user = userDao.getEmail(username);
		if (user.isPresent()) {
			return new org.springframework.security.core.userdetails.User(username, user.get().getPass(),
					new ArrayList<>());
		}
		throw new UsernameNotFoundException(username);
	}

	public PojoRes saveNoLogin(PojoUserReq data) {
		ConnHandler.begin();
		final User system = userDao.getUserByRole(EnumRole.SY.getCode()).get();

		if(verificationDao.getBycode(data.getVerificationCode(),data.getEmail()).isEmpty()) {
			throw new RuntimeException("Verification anda telah expired / email tidak cocok ");
		}
		
		final User user = new User();
		user.setEmail(data.getEmail());
		user.setVerificationCode(data.getVerificationCode());
		user.setPass(encoder.encode(data.getPass()));
		
		Profile profile = new Profile();
		profile.setAddress(data.getProfile().getAddress());
		profile.setFullName(data.getProfile().getFullName());
		profile.setPhoneNumber(data.getProfile().getPhoneNumber());
		profile.setBalance(BigDecimal.ZERO);
		Job job = new Job();
		job.setCompanyName(data.getProfile().getJob().getCompanyName());

		Industry industry = industryDao.getById(data.getProfile().getJob().getIndustryId()).get();
		Position position = positionDao.getById(data.getProfile().getJob().getPositionId()).get();
		job.setIndustry(industry);
		job.setPosition(position);
		job.setIsActive(true);
		Job jobInsert = jobDao.saveNoLogin(job, () -> system.getId());
		profile.setJob(jobInsert);

		profile.setIsActive(true);
		final Profile profileInsert = saveNoLogin(profile, ()-> system.getId());
		
		user.setProfile(profileInsert);

		for (int i = 0; i < data.getProfile().getProfileSocialMedia().size(); i++) {
			ProfileSocialMedia profileSocialMedia = new ProfileSocialMedia();
			profileSocialMedia.setProfile(profileInsert);
			SocialMedia socialMedia = socialMediaDao
					.getById(data.getProfile().getProfileSocialMedia().get(i).getSocialMediaId()).get();
			profileSocialMedia.setUsername(data.getProfile().getProfileSocialMedia().get(i).getUsername());
			profileSocialMedia.setSocialMedia(socialMedia);
			profileSocialMedia.setProfileLink(
					profileSocialMedia.getSocialMedia().getSocialMediaLink() + profileSocialMedia.getUsername());
			profileSocialMedia.setIsActive(true);
			profileSocialMediaDao.saveNoLogin(profileSocialMedia, () -> system.getId());
		}

		final Role role = roleDao.getById(data.getRoleId()).get();
		user.setRole(role);
		user.setIsActive(true);
	
		final User userInsert = saveNoLogin(user, () -> system.getId());

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("anda berhasil masuk " + userInsert.getEmail());

		new Thread(() -> emailSenderService.sendMail(data.getEmail(), "sudah teregister ", "Dear," + data.getEmail()
				+ " \n password Anda : " + data.getPass() + "\n Terimakasih untuk register Terimakasih")).start();
		ConnHandler.commit();
		return pojoRes;
		
	}

}
