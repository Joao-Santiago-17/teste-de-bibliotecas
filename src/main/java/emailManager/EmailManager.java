package emailManager;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.FileInputStream;
import java.util.Properties;


public class EmailManager {
    public static boolean sendEmail(String to, String subject, String body) {
        Properties props = new Properties();

        try {
            String configPath = System.getenv("EMAIL_CONFIG_PATH");
            try (FileInputStream fis = new FileInputStream(configPath)) {
                props.load(fis);
            }
            String adress = System.getenv("EMAIL_ADRESS");
            String password = System.getenv("EMAIL_PASSWORD");

            Session session = Session.getInstance(props, new Authenticator() {
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
