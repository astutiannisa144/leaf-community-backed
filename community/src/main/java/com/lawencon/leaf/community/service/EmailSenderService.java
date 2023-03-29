package com.lawencon.leaf.community.service;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.lawencon.leaf.community.model.Verification;


@Service
public class EmailSenderService{
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private ThymeLeafService thymeLeafService;
	@Autowired
	private TemplateEngine templateEngine;
	
	public void sendMail(final String toEmail, final String subject, final String body) {
		final SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("widia.astuti144@gmail.com");
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);
		message.setCc("wahyueron@gmaill.com");
		mailSender.send(message);
		
		System.out.println("Mail Sent Succesfullty...");
	}
	public void sendMailTest(final String toEmail, final String subject, final String body) {
		try {
			final MimeMessage message = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(
					message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name()
					);
			helper.setText(thymeLeafService.createContent("mail-sender-test.html", null),true);
			helper.setCc("wahyueron@gmaill.com");
			helper.setFrom("widia.astuti144@gmail.com");
			helper.setTo(toEmail);
			helper.setSubject(subject);
//			helper.setText(body);
			mailSender.send(message);
			System.out.println("Mail Sent Succesfullty...");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendMailVerification(Verification verification) throws MessagingException {
		Context context = new Context();
	    context.setVariable("verification", verification);
	    
	    String process = templateEngine.process("verification", context);
		MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Welcome " + verification.getEmail());

        String html = "<!doctype html>\n" +
                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
                "      xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\"\n" +
                "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "    <title>Email</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div>Welcome <b>" + verification.getEmail() + "</b></div>\n" +
                "\n" +
                "<div> Your username is <b>" + verification.getEmail() + "</b></div>\n" +
                "</body>\n" +
                "</html>\n";
        
        helper.setText(process, true);
        helper.setTo(verification.getEmail());
        mailSender.send(mimeMessage);
	}
}
