package SmartNavigationSystem;

import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

public class SendEmail {
    public static void sendEmail(String to, String from, String pwd, String subjecct, String msg) {

        // Recipient's email ID needs to be mentioned.
        // String to
  
        // Sender's email ID needs to be mentioned
        // String from 
  
        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";
  
        // Get system properties
        Properties properties = System.getProperties();
  
        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); 
  
        // Get the Session object.// and pass username and password
      //   Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
      //      protected PasswordAuthentication getPasswordAuthentication() {
      //         return new PasswordAuthentication(from, pwd);
      //      }
      //   });
  
        // Used to debug SMTP issues
      //   session.setDebug(true);
  
      //   try {
      //      MimeMessage message = new MimeMessage(session);
      //      message.setFrom(new InternetAddress(from));
      //      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      //      message.setSubject(subjecct);
      //      message.setText(msg);
  
      //      System.out.println("sending...");
  
      //      Transport.send(message);
      //      System.out.println("Sent message successfully....");
      //   } catch (MessagingException mex) {
      //      mex.printStackTrace();
      //   }
  
  
     }
  
}
