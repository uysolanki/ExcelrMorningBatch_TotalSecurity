package com.excelr.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excelr.cms.entity.Customer;
import com.excelr.cms.entity.User;
import com.excelr.cms.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userservice;
	
	@RequestMapping("/showFormToAddUser")
	public String showFormToAddUser(Model model)
	{
		User user=new User();
		model.addAttribute("user",user);
		return "user-form";
	}
	
	@PostMapping("/adduser")
	public String adduser(@ModelAttribute User user)
	{
		userservice.addUser(user);
		return "redirect:/listOfCustomers";
	}
}
