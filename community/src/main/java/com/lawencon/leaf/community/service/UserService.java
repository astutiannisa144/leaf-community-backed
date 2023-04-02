package com.lawencon.leaf.community.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.constant.EnumRole;
import com.lawencon.leaf.community.dao.BankAccountDao;
import com.lawencon.leaf.community.dao.IndustryDao;
import com.lawencon.leaf.community.dao.JobDao;
import com.lawencon.leaf.community.dao.PositionDao;
import com.lawencon.leaf.community.dao.RoleDao;
import com.lawencon.leaf.community.dao.UserDao;
import com.lawencon.leaf.community.dao.VerificationDao;
import com.lawencon.leaf.community.model.BankAccount;
import com.lawencon.leaf.community.model.Industry;
import com.lawencon.leaf.community.model.Job;
import com.lawencon.leaf.community.model.Position;
import com.lawencon.leaf.community.model.Profile;
import com.lawencon.leaf.community.model.Role;
import com.lawencon.leaf.community.model.User;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.bank.account.PojoBankAccountRes;
import com.lawencon.leaf.community.pojo.user.PojoUserReq;
import com.lawencon.security.principal.PrincipalService;

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
	@Autowired
	private BankAccountDao bankAccountDao;
	
	private final PrincipalService pricipalService;

	public UserService(UserDao userDao, RoleDao roleDao, PasswordEncoder encoder, EmailSenderService emailSenderService,
			IndustryDao industryDao, PositionDao positionDao, JobDao jobDao,PrincipalService pricipalService) {
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.encoder = encoder;
		this.emailSenderService = emailSenderService;
		this.industryDao = industryDao;
		this.positionDao = positionDao;
		this.jobDao = jobDao;
		this.pricipalService=pricipalService;
	}
	
	private void valIdNull(PojoUserReq user) {
		if(user.getId()!=null) {
			throw new RuntimeException("Id must be empty");
		}
	}
	
	private void valBkNotNull(PojoUserReq user) {
		if(user==null) {
			throw new RuntimeException("Object not found");
		}
		if(user.getEmail()==null) {
			throw new RuntimeException("Email cannot be empty");
		}
	
	}

	
	private void valNonBk(PojoUserReq user) {
		if(user.getPass()==null) {
			throw new RuntimeException("Password cannot be empty");
		}
		if(user.getVerificationCode()==null) {
			throw new RuntimeException("Verification Code  cannot be empty");
		}
		if(user.getProfile().getFullName()==null) {
			throw new RuntimeException("Name cannot be empy");
		}
		if(user.getProfile().getPhoneNumber()==null) {
			throw new RuntimeException("Phone number cannot be empty");
		}
		if(user.getProfile().getAddress()==null) {
			throw new RuntimeException("Address cannot be empty");
		}
		if(user.getProfile().getJob().getCompanyName()==null) {
			throw new RuntimeException("Company field cannot be empty");
		}
		if(user.getProfile().getJob().getIndustryId()==null) {
			throw new RuntimeException("Industry Field cannot be empty");
		}
		if(user.getProfile().getJob().getPositionId()==null) {
			throw new RuntimeException("Position cannot be empty");
		}
	}
	
	private void valIdNotNull(PojoUserReq user) {
		if(user.getId()==null) {
			throw new RuntimeException("Id cannot be empty");
		}
	}
	private void valIdExist(PojoUserReq user) {
		if(userDao.getById(user.getId()).isEmpty()) {
			throw new RuntimeException("Id Does Not Exist ");
		}
	}
	private void valBkNotChange(PojoUserReq data) {
		User user = userDao.getById(data.getId()).get();
		if(data.getEmail()!=user.getEmail()) {
			throw new RuntimeException("Can't update email");
		}
		
	}
	
	public Optional<User> login(String email) {
		return userDao.getEmail(email);
	}
	public PojoBankAccountRes getByIdBank() {
		final User system = userDao.getUserByRole(EnumRole.SY.getCode()).get();

		BankAccount bankAccount = bankAccountDao.getById(system.getId()).get();
		PojoBankAccountRes res = new PojoBankAccountRes();
		res.setId(bankAccount.getId());
		res.setAccountNumber(bankAccount.getAccountNumber());
		res.setBankName(bankAccount.getBankName());
		res.setFileId(bankAccount.getFile().getId());
		res.setUserId(bankAccount.getUser().getId());
		return res;
		
	}
	private void valNonBkUpdate(PojoUserReq user) {
		if(user.getOldPass()==null) {
			throw new RuntimeException("Old Password is Empty");
		}
		if(user.getNewPass()==null) {
			throw new RuntimeException("New Password Is Empty");
		}
	
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
		valIdNull(data);
		valBkNotNull(data);

		valNonBk(data);
		final User system = userDao.getUserByRole(EnumRole.SY.getCode()).get();
		try {
			pricipalService.getAuthPrincipal();
			final User user = new User();
			user.setEmail(data.getEmail());
			user.setVerificationCode(data.getVerificationCode());
			user.setPass(encoder.encode(data.getPass()));
			
			
			Profile profile = new Profile();
			profile.setAddress(data.getProfile().getAddress());
			profile.setFullName(data.getProfile().getFullName());
			profile.setPhoneNumber(data.getProfile().getPhoneNumber());
			profile.setBalance(BigDecimal.ZERO);
			profile.setIsActive(true);
			final Profile profileInsert = save(profile);
			user.setProfile(profileInsert);

			final Role role = roleDao.getBycode(EnumRole.AD.getCode()).get();
			user.setRole(role);
			user.setIsActive(true);

			save(user);
			
		} catch (Exception e) {
			if (verificationDao.getBycode(data.getVerificationCode(), data.getEmail()).isEmpty()) {
				throw new RuntimeException("Verification code already expired / email didn't match");
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
			final Profile profileInsert = saveNoLogin(profile, () -> system.getId());

			user.setProfile(profileInsert);


			final Role role = roleDao.getBycode(EnumRole.MB.getCode()).get();
			user.setRole(role);
			user.setIsActive(true);

			saveNoLogin(user, () -> system.getId());
			new Thread(() -> {
				try {
					emailSenderService.sendMailRegister(user);
				} catch (MessagingException er) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			})
			.start();
		}
		
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Registration Completed" + data.getEmail());

		
		ConnHandler.commit();
		return pojoRes;

	}

	public PojoRes update(PojoUserReq data) {
		ConnHandler.begin();
		valIdNotNull(data);
		valIdExist(data);
		valBkNotNull(data);
		valBkNotChange(data);
		valNonBkUpdate(data);
		
		final User user = userDao.getByIdAndDetach(User.class, pricipalService.getAuthPrincipal());
		if (encoder.matches(data.getOldPass(), user.getPass()) == false) {
			throw new RuntimeException("Password didn't match");
		}
		
		user.setPass(encoder.encode(data.getNewPass()));

		user.setIsActive(true);
		user.setVer(data.getVer());
		userDao.save(user);


		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Password updated");

		ConnHandler.commit();

		return pojoRes;
	}
	
	public PojoRes delete(String id) {
		
		try {
			ConnHandler.begin();
			userDao.deleteById(User.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("User deleted");
		return pojoRes;
	}

}
