package com.vti.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

@Service
public class SendMailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private MessageSource messageSource;

    public void sendMessage(String to, String subject, String text) {

        try {
            MimeMessage message = emailSender.createMimeMessage();
            message.setSubject(subject);
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom("vti.test001@gmail.com");
            helper.setTo(to);

            helper.setText(text, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void sendForgotPassword(String email, String random, String lang) {
        String subject = messageSource.getMessage("mail.otp.subject", null, Locale.forLanguageTag(lang));
        String content = messageSource.getMessage("mail.otp.content", new Object[]{random}, Locale.forLanguageTag(lang));
        sendMessage(email, subject, content);
    }
}
