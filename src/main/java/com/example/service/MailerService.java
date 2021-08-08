package com.example.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailerService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String to, String sender, String subject, String text, Boolean isSimpleMail, String template) {
		var msg = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = null;
			if (isSimpleMail.booleanValue()) {
				helper = new MimeMessageHelper(msg);
				helper.setText(text);
			} else {
				helper = new MimeMessageHelper(msg, true);
				var templateTemp = template != null ? template : getDefaultTemplate();
				helper.setText(text, templateTemp);
			}
			helper.setTo(to);
			helper.setFrom(sender);
			helper.setSubject(subject);
			helper.setSentDate(new Date());
			javaMailSender.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getDefaultTemplate() {
		try {
			return Files.readString(Path.of("src/main/resources/templates/email-template.html"),
					Charset.defaultCharset());
		} catch (IOException e) {
			return "";
		}
	}

}
