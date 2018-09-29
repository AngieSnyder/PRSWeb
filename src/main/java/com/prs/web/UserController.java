package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prs.business.user.User;
import com.prs.business.user.UserRepository;

@Controller
@RequestMapping("/Users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/List")
	public @ResponseBody Iterable<User> getAllUser() {
		Iterable<User> users = userRepository.findAll();
		return users;
	}
	
	@GetMapping("/Get")
	public @ResponseBody Optional<User> getUser(@RequestParam int id) {
		Optional<User>user = userRepository.findById(id);
		return user;
	}
	
	@PostMapping("/Add")
	public @ResponseBody User addUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PostMapping("/Change")
	public @ResponseBody User updateUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PostMapping("/Remove")
	public @ResponseBody String removeUser(@RequestBody User user) {
		userRepository.delete(user);
		return "user deleted";
	}

}