package com.example.service;

import org.springframework.stereotype.Service;

import com.example.annotations.BaseMail;

@Service
public class UserService {

	@BaseMail
	public void sendMailGoodBye(String to, String sender, String subject, String text, Boolean isSimpleMail,
			String template) {
	}

	@BaseMail
	public String sendMailGreeting(String to, String from, String subject, String text) {
		return "Saludo por correo enviado";
	}
}
