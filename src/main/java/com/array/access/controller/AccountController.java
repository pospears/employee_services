package com.array.access.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.array.access.account.model.User;
import com.array.access.account.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//Allow user to sign up with an user name and password 
//Allow user to login at root context with user name and password
//Inform the user when their login credentials are not valid.
//Allow user to logout from members context
@RestController
public class AccountController {

	@Autowired
	private UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	// Allows a user to login or auto sign-up after rejecting any invalid user
	// accounts or credentials attempts
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login(@ModelAttribute("user") User user) {

		logger.info(String.format("Accessing /login"));

		if (!user.validUserAccount() || userService.userExists(user) && !userService.credentialsMatch(user)) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("index");
			modelAndView.addObject("message", !user.validUserAccount() ? String.format("Invalid user account provided")
					: String.format("Invalid credentials"));
			return modelAndView;
		} else if (userService.userExists(user)) {
			userService.login(user);
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("members");
			modelAndView.addObject("user", user);
			modelAndView.addObject("message", String.format("(online)"));
			return modelAndView;
		} else if (!userService.userExists(user)) {
			userService.signup(user);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("members");
			modelAndView.addObject("user", user);
			modelAndView.addObject("message", String.format("(online - auto registered)"));
			return modelAndView;
		}

		return new ModelAndView("/");
	}

	// Allows user to log out and redirect the user to this page upon successful
	// logout.
	@RequestMapping(value = "/logout", method = { RequestMethod.POST })
	public ModelAndView logout(@ModelAttribute User user) {
		logger.info(String.format("Accessing /logout..."));

		userService.logout(user);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		modelAndView.addObject("message", String.format("Successful logoff %s", user.getEmail()));
		return modelAndView;

	}
}
