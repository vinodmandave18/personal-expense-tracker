package com.mycompany.personalexpensetracker;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailUtility {

    // Update these with your actual SMTP email and app password
    private static final String FROM_EMAIL = "your-email@gmail.com";       // Your email address
    private static final String APP_PASSWORD = "your-app-password";        // App password or SMTP password

    public static void sendEmail(String to, String subject, String body) throws MessagingException {

        // SMTP server configuration (Gmail example)
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create authenticator for SMTP login
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
            }
        };

        // Get mail session
        Session session = Session.getInstance(props, auth);

        // Compose email message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM_EMAIL));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject(subject);
        msg.setText(body);

        // Send the email
        Transport.send(msg);
        System.out.println("Email sent successfully to " + to);
    }
}
