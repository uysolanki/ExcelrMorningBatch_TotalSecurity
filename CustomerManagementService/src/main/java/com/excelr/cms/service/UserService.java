package com.excelr.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.excelr.cms.entity.User;
import com.excelr.cms.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userrepo;
	
	
			
	public void addUser(User user) 
	{
		PasswordEncoder encoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String temppassword=encoder.encode(user.getPassword()); 
		user.setPassword(temppassword.substring(8));
		userrepo.save(user);	
	}

}
