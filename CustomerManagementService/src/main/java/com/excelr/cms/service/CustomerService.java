package com.excelr.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excelr.cms.entity.Customer;
import com.excelr.cms.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository custrepo;

	public void addcustomer(Customer customer) {
		custrepo.save(customer);
		
	}

	public List<Customer> listOfCustomers() {
		return custrepo.findAll();
	}

	public void deletecustomer(int custid) {
		custrepo.deleteById(custid);
		
	}

	public Customer getCustomerById(int custid) {
		return custrepo.findById(custid).get();
	}

	public void updatecustomer(int custid, Customer customer) {
		Customer customerfromdb=custrepo.findById(custid).get();
		customerfromdb.setFirstName(customer.getFirstName());
		customerfromdb.setLastName(customer.getLastName());
		customerfromdb.setEmail(customer.getEmail());
		
		
		custrepo.save(customerfromdb);
		
	}

}
