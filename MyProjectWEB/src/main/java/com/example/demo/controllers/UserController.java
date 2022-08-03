package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositories.UserRepository;

import model.User;

@RestController
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	UserRepository uRep;
	
	@PostMapping("/saveUser")
	public User saveUser(@RequestBody User user) {
		return uRep.save(user);
	}
}
