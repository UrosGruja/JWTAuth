package com.example.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositories.UserRepository;
import com.example.demo.security.CustomUserDetail;
import com.example.demo.security.DetailUserService;
import com.example.demo.security.JWTUtil;

import model.User;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	UserRepository uRep;

	@Autowired
	JWTUtil jwt;

	@Autowired
	DetailUserService userService;

	@Autowired
	AuthenticationManager aManager;

	@PostMapping("/register")
	public User saveUser(@RequestBody User user) {
		return uRep.save(user);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
		try {
			AuthResponse response = new AuthResponse();

			User user = uRep.findByUserEmail(request.getEmail());
			String token = jwt.genereteToken(user);
			response.setEmail(user.getEmail());
			response.setName(user.getName());
			response.setJwt(token);

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/me")
	public ResponseEntity<AuthResponse> loggedUser(@RequestBody AuthRequest request) {
		try {

			AuthResponse response = new AuthResponse();
//			aManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
			CustomUserDetail userDetail = (CustomUserDetail) userService.loadUserByUsername(request.getEmail());

			response.setEmail(userDetail.getUsername());
			response.setName(userDetail.getName());
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}
}
