package com.example.demo.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	  @Autowired
	  UserRepository userRepository;
	  public void updateResetPasswordToken(String token, String email) {
		  User customer = userRepository.findByEmail(email);
	      if (customer != null) {
	          customer.setResetPasswordToken(token);
	          userRepository.save(customer);
	      } 
	  }
	   
	  public User getByResetPasswordToken(String token) {
	      return userRepository.findByResetPasswordToken(token);
	  }
	   
	  public void updatePassword(User customer, String newPassword) {
	      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	      String encodedPassword = passwordEncoder.encode(newPassword);
	      customer.setPassword(encodedPassword);
	       
	      customer.setResetPasswordToken(null);
	      userRepository.save(customer);
	  }
}
