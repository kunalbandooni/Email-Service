package com.example.smtp.service.impl;

import com.example.smtp.dto.EmailRequestBody;
import com.example.smtp.exceptions.MimeHelperException;
import com.example.smtp.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendSimpleMessage(EmailRequestBody emailRequestBody) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(emailRequestBody.getTo());
            helper.setSubject(emailRequestBody.getSubject());
            helper.setText(emailRequestBody.getBody(), false);
            mailSender.send(message);
        } catch (Exception e) {
            throw new MimeHelperException(e.getMessage());
        }
    }

    @Override
    public void sendHtmlMessage(EmailRequestBody emailRequestBody) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(emailRequestBody.getTo());
            helper.setSubject(emailRequestBody.getSubject());
            helper.setText(emailRequestBody.getBody(), true);
            mailSender.send(message);
        } catch (Exception e) {
            throw new MimeHelperException(e.getMessage());
        }
    }

}
