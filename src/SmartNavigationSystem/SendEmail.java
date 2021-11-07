package SmartNavigationSystem;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
   public static void sendEmail(String to, String subject, String msg) throws MessagingException {

      String from = "cs3343g20system@gmail.com";
      String pwd = "Abcd1234!";
      String host = "smtp.gmail.com";

      // system properties
      Properties properties = System.getProperties();
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.smtp.auth", "true");
      // mail server
      properties.setProperty("mail.smtp.host", host);

      // default session object
      Session session = Session.getDefaultInstance(properties);

         MimeMessage message = new MimeMessage(session);

         message.setFrom(new InternetAddress(from));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
         message.setSubject(subject);
         message.setText(msg);

         Transport.send(message, from, pwd);
         System.out.print("Sent message successfully....\n");
      
   }
}