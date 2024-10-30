package org.borgoltz.lutins;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class Emailer {

	public static void main(String[] args) {
		new Emailer().email("alexandre.borgoltz@gmail.com", "test", "<div>Alors...</div><div>Ca marche ????</div>");
	}

	public void email(String to, String subject, String htmlbody) {
		final String username = "b731052c595dc1";
		final String password = "4d05d7af4d0f2a";

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
		prop.put("mail.smtp.port", "25");
		prop.put("mail.smtp.ssl.trust", "sandbox.smtp.mailtrap.io");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("aleaxndre.borgoltz@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);


			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(htmlbody, "text/html; charset=utf-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);

			message.setContent(multipart);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
