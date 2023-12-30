package com.isg.ws.email;

import com.isg.ws.configuration.HoaxifyProperties;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {
    @Autowired
    HoaxifyProperties properties;

    @Value("${hoaxify.email.host}")
    String host;
    JavaMailSenderImpl mailSender;

    @Autowired
    MessageSource messageSource;

    String activationEmail= """
            <html>
            <body>
            <h1>${title}</h1>
            <a href="${url}">${clickHere}<a/>
            
            </body>
            </html>
            """;


    public void sendActivationMail(String email,String activationToken) {
        var activationUrl=properties.getClient().host()+"/activation/"+activationToken;
        var title=messageSource.getMessage("hoaxify.mail.user.created.title",null, LocaleContextHolder.getLocale());
        var clickHere=messageSource.getMessage("hoaxify.mail.clicl.here",null,LocaleContextHolder.getLocale());

        var mailBody=activationEmail
                .replace("${url}",activationUrl)
                .replace("${title}",title)
                .replace("${clickHere}",clickHere);
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper mailMessage=new MimeMessageHelper(mimeMessage);


        try {
            mailMessage.setFrom(properties.getEmail().from());
            mailMessage.setTo(email);
            mailMessage.setSubject(title);
            mailMessage.setText(mailBody,true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        this.mailSender.send(mimeMessage);
    }
    @PostConstruct
    public void initialize(){
        this.mailSender=new JavaMailSenderImpl();
        mailSender.setHost(properties.getEmail().host());
        mailSender.setPort(properties.getEmail().port());
        mailSender.setUsername(properties.getEmail().username());
        mailSender.setPassword(properties.getEmail().password());

        Properties properties=mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable","true");

    }



}
