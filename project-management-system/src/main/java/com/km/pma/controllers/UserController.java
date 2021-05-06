package com.km.pma.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.km.pma.entities.User;
import com.km.pma.repositories.UserRepository;

@Controller
public class UserController {
	
	private final UserRepository userRepo;
	
	@Autowired
	public UserController(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@GetMapping("/index")
	public String showUserList(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "index";
	}

	@GetMapping("/signup")
	public String showSignUpForm(User user) {
		return "add-user";
	}
	
	@PostMapping("/adduser")
	public String addUSer(@Valid User user,  BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "add-user";
		}
		
		userRepo.save(user);
		return "redirect:/index";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpadteForm(@PathVariable("id") long id, Model model) {
		User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
		model.addAttribute("user", user);
		
		return "update-user";
	}
	
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			user.setId(id);
			return "update-user";
		}
		
		userRepo.save(user);
		
		return "redirect:/index";
	}
	
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepo.delete(user);
        
        return "redirect:/index";
    }
}
