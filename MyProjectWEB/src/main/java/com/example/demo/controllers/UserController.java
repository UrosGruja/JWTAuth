package com.example.demo.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositories.UserRepository;

import model.User;


@RestController
//@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	UserRepository uRep;
	
	@GetMapping("/users/{id}")
	@ResponseBody
    public User getUserById(@PathVariable Integer id) {
        return uRep.findByUseridUser(id);
    }


	@GetMapping("/users")
	Page<User> getUsers(
			@RequestParam Optional<Integer> page,
			@RequestParam Integer limit,
			@RequestParam Optional<String> sort){
		return uRep.findAll(
				PageRequest.of(
						page.orElse(0),
						limit,
						Sort.Direction.ASC, sort.orElse("id")
						)
				);
	}
}
