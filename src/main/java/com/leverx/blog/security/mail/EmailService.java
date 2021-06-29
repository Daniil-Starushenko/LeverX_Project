package com.leverx.blog.security.mail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
@PropertySource("classpath:application.properties")
public class EmailService implements EmailSender{

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.from}")
    private String mailFrom;

    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom(mailFrom);
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            log.error("faild to send email", e );
            throw new IllegalStateException("failed to send email");
        }
    }
}
