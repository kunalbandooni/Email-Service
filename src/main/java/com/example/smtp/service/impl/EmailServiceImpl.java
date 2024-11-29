package com.example.smtp.service.impl;

import com.example.smtp.dto.EmailRequestBody;
import com.example.smtp.exceptions.ClassConversionException;
import com.example.smtp.exceptions.MimeHelperException;
import com.example.smtp.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

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

    @Override
    public void sendMessageWithAttachment(EmailRequestBody emailRequestBody, MultipartFile file) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailRequestBody.getTo());
            helper.setSubject(emailRequestBody.getSubject());
            helper.setText(emailRequestBody.getBody());
            helper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), file);

            mailSender.send(message);
        } catch (Exception e) {
            throw new MimeHelperException(e.getMessage());
        }
    }

    @Override
    public void sendMessageWithInlineImage(EmailRequestBody emailRequestBody, MultipartFile image) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailRequestBody.getTo());
            helper.setSubject(emailRequestBody.getSubject());
            helper.setText(emailRequestBody.getBody(), true);

            helper.addInline("image", convertMultipartFileToFile(image));

            mailSender.send(message);
        } catch (IOException e) {
            throw new ClassConversionException(e.getMessage());
        } catch (Exception e) {
            throw new MimeHelperException(e.getMessage());
        }
    }

    public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        }
        return file;
    }

}
