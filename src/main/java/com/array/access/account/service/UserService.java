package com.array.access.account.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.array.access.account.model.User;
import com.array.access.account.dao.UserRepository;

@Component(value = "userService")
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	public void login(User user) {

		logger.info(String.format("Attempting login for %s ...", user.getEmail()));
		if (userExists(user) && credentialsMatch(user)) {
			
			user = userRepository.findByEmail(user.getEmail()).get(0);
			
			if(!user.getUserLoggedIn())
			{
				user.setUserLoggedIn(true);
				userRepository.save(user);
			}
			else
				logger.info(String.format("%s already logged in", user.getEmail()));
			
		}
	}

	public void signup(User user) {
		logger.info(String.format("Attempting sign up for %s ...", user.getEmail()));
		if (!userExists(user) && user.validUserAccount()) {
			logger.info(String.format("Signup successful for %s ...", user.getEmail()));
			user.setUserLoggedIn(true);
			userRepository.save(user);
		}
	}


	public void logout(User user) {
		logger.info(String.format("Attempting sign out for %s ...", user.getEmail()));
		
			if (userExists(user)) {
			
			user = userRepository.findByEmail(user.getEmail()).get(0);
			
			if(user.getUserLoggedIn())
			{
				user.setUserLoggedIn(false);
				userRepository.save(user);
			}
			else
				logger.info(String.format("%s already logged out", user.getEmail()));
			
		}
			else
				logger.info(String.format("%s does not exist", user.getEmail()));
		
	}

	public boolean userExists(User user) {

		if (userRepository.findByEmail(user.getEmail()) == null || userRepository.findByEmail(user.getEmail()).isEmpty() )
			return false;

		return true;
	}

	public boolean credentialsMatch(User user)
	{
		if (userExists(user) && userRepository.findByEmail(user.getEmail()).stream().anyMatch(u -> u.getEmail().equals(user.getEmail())) &&
	 			userRepository.findByEmail(user.getEmail()).stream().anyMatch(u -> u.getPassword().equals(user.getPassword()))) {
			logger.info(String.format("Credentials match for %s ...", user.getEmail()));
			return true;
		}
		else
		{
			logger.info(String.format("Credentials do not match for %s ...", user.getEmail()));
			return false;
		}
	}
}
