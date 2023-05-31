package lk.ijse.project_wineverse.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailAttachment {
    public static void emailAttachmentSend(String recipient, String subject, String body, String attachmentPath) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        String username = "fightlife241@gmail.com";
        String password = "genbtmifwmmkcrsx";

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);

        // Create a multipart message
        Multipart multipart = new MimeMultipart();

        // Create the message body part
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(body);

        // Add the message body part to the multipart message
        multipart.addBodyPart(messageBodyPart);

        // Create the PDF attachment body part
        BodyPart pdfBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(attachmentPath);
        pdfBodyPart.setDataHandler(new DataHandler(source));
        pdfBodyPart.setFileName(source.getName());

        // Add the PDF attachment body part to the multipart message
        multipart.addBodyPart(pdfBodyPart);

        // Set the content of the message as the multipart message
        message.setContent(multipart);

        // Send the email
        Transport.send(message);
    }
}
