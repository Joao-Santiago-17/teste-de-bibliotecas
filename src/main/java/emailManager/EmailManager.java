package emailManager;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

@ApplicationScoped
public class EmailManager {
    public boolean sendEmail(String to, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        try {
        String adress = System.getenv("EMAIL_ADRESS");
        String password = System.getenv("EMAIL_PASSWORD");
        //props.load(getClass().getClassLoader().getResourceAsStream("config/email.properties"));



            System.out.println(
                    "Enviando e-mail de " + adress + ":" + password
            );

        Session session = Session.getInstance(props,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(adress, password);
            }
        });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(adress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
