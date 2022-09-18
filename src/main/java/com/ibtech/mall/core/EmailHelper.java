package com.ibtech.mall.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EmailHelper {
    private static final String MAIL_USER = System.getenv("MAIL_USER");
    private static final String MAIL_FROM = System.getenv("MAIL_FROM");
    private static final String MAIL_PASS = System.getenv("MAIL_PASS");
    private static final String MAIL_HOST = System.getenv("MAIL_HOST");
    private static final String MAIL_PORT = System.getenv("MAIL_PORT");
    private static Logger logger = LoggerFactory.getLogger(EmailHelper.class);

    public static void sendEmail(String toMailAddress, String mailSubject, String mailTemplate) {

        Properties prop = new Properties();
        prop.put("mail.smtp.host", MAIL_HOST);
        prop.put("mail.smtp.port", MAIL_PORT);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(MAIL_USER, MAIL_PASS);
                    }
                });
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\ibtech\\mall\\core\\template\\" + mailTemplate));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();


            String content = contentBuilder.toString();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MAIL_FROM));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toMailAddress)
            );
            message.setSubject(mailSubject);
            message.setText(content);

            Transport.send(message);

            logger.info("Email send successfully");

        } catch (MessagingException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
