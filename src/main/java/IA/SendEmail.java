package IA;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
public class SendEmail {

    public static void sendVehicleStatusEmail(String recipientEmail, String newStatus) {
        // Sender info
        final String senderEmail = "turkistaniyusuf@gmail.com";
        final String senderPassword = "odoturzhyfwgvcbk";

        // SMTP server settings (Gmail)
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // enables STARTTLS
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a session
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail, "Bicycle Registration System"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Updated Vehicle Status");
            message.setText("Your vehicle status has been updated to: " + newStatus);

            // Send the email
            Transport.send(message);

            System.out.println("Email successfully sent to " + recipientEmail);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error sending email: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}