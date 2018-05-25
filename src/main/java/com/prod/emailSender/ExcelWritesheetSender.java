package com.prod.emailSender;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@PropertySource("Converter.properties")
public class ExcelWritesheetSender {

	Logger logger = Logger.getLogger(ExcelWritesheetSender.class);

	@Value("${file.output.location}")
	private String fileLocation;

	@Autowired
	public JavaMailSender emailSender;

	public void sendFile(String recieverEmail) {
		MimeMessage message = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(recieverEmail);
			helper.setSubject("Report Sent At: " + new Date().toString());
			helper.setText("");
			FileSystemResource file = new FileSystemResource(new File(fileLocation));
			logger.error("File Length: "+file.contentLength());
			helper.addAttachment("WriteSheet.xlsx", file);
		} catch (MessagingException e) {
			SimpleMailMessage errorEmailMessage = new SimpleMailMessage();
			errorEmailMessage.setTo(recieverEmail);
			errorEmailMessage.setSubject("ERROR SENDING REQUEST");
			errorEmailMessage
					.setText("There was an error sending the writesheet, here is the error: " + e.getMessage());
			emailSender.send(message);

			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		emailSender.send(message);
	}
}
