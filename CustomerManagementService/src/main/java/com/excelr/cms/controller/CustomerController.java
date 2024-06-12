package com.excelr.cms.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excelr.cms.entity.Customer;
import com.excelr.cms.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerservice;
	
	@RequestMapping("/listOfCustomers")
		public String listOfCustomers(Model model)
		{
		List<Customer> customerlist=customerservice.listOfCustomers();
		model.addAttribute("customers",customerlist);
		return "customer-list";
		}
	
	
	@RequestMapping("/showFormToAddCustomer")
	public String showFormToAddCustomer(Model model)
	{
		Customer customer=new Customer();
		model.addAttribute("customer",customer);
		return "customer-form";
	}
	
	@PostMapping("/addcustomer")
	public String addcustomer(@ModelAttribute Customer customer)
	{
		customerservice.addcustomer(customer);
		return "redirect:/listOfCustomers";
	}

	@RequestMapping("/deletecustomer/{id}")
	public String deletecustomer(@PathVariable("id") int custid)
	{
		customerservice.deletecustomer(custid);
		return "redirect:/listOfCustomers";
	}
	
	@RequestMapping("/updatecustomerform/{id}")
	public String updatecustomerform(@PathVariable("id") int custid, Model model)
	{
		Customer customer=customerservice.getCustomerById(custid);
		model.addAttribute("customer",customer);
		return "edit-customer";
	}
	
	@RequestMapping("/updatecustomer/{id}")
	public String updatecustomer(@PathVariable("id") int custid, @ModelAttribute Customer customer)
	{
		customerservice.updatecustomer(custid,customer);
		return "redirect:/listOfCustomers";
	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			    "you do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}
}
