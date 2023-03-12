package com.lawencon.leaf.community.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.leaf.community.model.User;
import com.lawencon.leaf.community.pojo.PojoRes;
import com.lawencon.leaf.community.pojo.user.PojoLoginReq;
import com.lawencon.leaf.community.pojo.user.PojoLoginRes;
import com.lawencon.leaf.community.pojo.user.PojoUserReq;
import com.lawencon.leaf.community.service.JwtService;
import com.lawencon.leaf.community.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	private final UserService userService;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
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

		return new ResponseEntity<>(loginRes, HttpStatus.OK);
	}
	
	@PostMapping("register")
	public ResponseEntity<PojoRes> insert(final @Validated @RequestBody PojoUserReq data) {
		final PojoRes res = userService.saveNoLogin(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

}
