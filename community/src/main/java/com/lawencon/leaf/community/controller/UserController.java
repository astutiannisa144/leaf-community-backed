package com.lawencon.leaf.community.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.leaf.community.model.User;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.bank.account.PojoBankAccountRes;
import com.lawencon.leaf.community.pojo.user.PojoLoginReq;
import com.lawencon.leaf.community.pojo.user.PojoLoginRes;
import com.lawencon.leaf.community.pojo.user.PojoUserReq;
import com.lawencon.leaf.community.service.JwtService;
import com.lawencon.leaf.community.service.UserPremiumService;
import com.lawencon.leaf.community.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	private final UserService userService;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final UserPremiumService userPremiumService;
 
	public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager,
			UserPremiumService userPremiumService) {
		this.userService = userService;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
		this.userPremiumService=userPremiumService;
	}

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody final PojoLoginReq user) {

		final Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPass());

		authenticationManager.authenticate(auth);

		final Optional<User> userOptional = userService.login(user.getEmail());

		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, 12);

		final Map<String, Object> claims = new HashMap<>();
		claims.put("exp", cal.getTime());
		claims.put("id", userOptional.get().getId());

		final PojoLoginRes loginRes = new PojoLoginRes();
		loginRes.setToken(jwtService.generateJwt(claims));
		loginRes.setUserId(userOptional.get().getId());
		loginRes.setFullName(userOptional.get().getProfile().getFullName());
		loginRes.setRoleCode(userOptional.get().getRole().getRoleCode());
		loginRes.setProfileId(userOptional.get().getProfile().getId());
		if (userOptional.get().getProfile().getFile() != null) {
			loginRes.setFileId(userOptional.get().getProfile().getFile().getId());
		}
		if(userPremiumService.getByUserPurchased(userOptional.get().getId()).isPresent()) {
			loginRes.setIsPremium(true);
		}else {
			loginRes.setIsPremium(false);
		}
		return new ResponseEntity<>(loginRes, HttpStatus.OK);
	}
	
	@PostMapping("register")
	public ResponseEntity<PojoRes> registerMember(final @Validated @RequestBody PojoUserReq data) {
		final PojoRes res = userService.saveNoLogin(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	@PostMapping()
	public ResponseEntity<PojoRes> registerAdmin(final @Validated @RequestBody PojoUserReq data) {
		final PojoRes res = userService.saveNoLogin(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PatchMapping()
	public ResponseEntity<PojoRes> changePassword(final @Validated @RequestBody PojoUserReq data) {
		final PojoRes res = userService.update(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> delete(final @Valid @PathVariable String id) {
		final PojoRes res = userService.delete(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	@GetMapping("/bank")
	public ResponseEntity<PojoBankAccountRes> getBank() {
		final PojoBankAccountRes res = userService.getByIdBank();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
