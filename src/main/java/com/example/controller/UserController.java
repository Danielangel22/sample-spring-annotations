package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.annotations.SnakeCase;
import com.example.annotations.TrackTime;
import com.example.annotations.UpperCase;
import com.example.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@TrackTime
	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public String helloWorld() {
		return "Hello World";
	}

	@UpperCase
	@SnakeCase
	@TrackTime
	@GetMapping(value = "/string", produces = MediaType.APPLICATION_JSON_VALUE)
	public String toUpperCase(@RequestParam String name) {
		return "Hello World " + name;
	}

	@TrackTime
	@GetMapping(value = "/users/mail", produces = MediaType.APPLICATION_JSON_VALUE)
	public String sendMail(@RequestParam String to, @RequestParam String from, @RequestParam String subject,
			@RequestParam String text) {
		return userService.sendMailGreeting(null, from, subject, text);
	}

	@TrackTime
	@GetMapping(value = "/users/goodbye", produces = MediaType.APPLICATION_JSON_VALUE)
	public String goodbye() {
//		var templateTemp = MailerService.getDefaultTemplate();
//		templateTemp = templateTemp.replace("{bodyMessage}", "Variables parametrizadas");
		userService.sendMailGoodBye("trabajos.daniel2018@gmail.com", "trabajos.daniel2018@gmail.com", "Despedida",
				"Adios amigo adios gracias por ver el tuto", true, null);
		return "Good bye  World";
	}
}
