package com.lawencon.leaf.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailSenderService{
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMail(final String toEmail, final String subject, final String body) {
		final SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("widia.astuti144@gmail.com");
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);
		
		mailSender.send(message);
		
		System.out.println("Mail Sent Succesfullty...");
	}

}
