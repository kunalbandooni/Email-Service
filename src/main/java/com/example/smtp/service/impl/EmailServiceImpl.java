package com.example.smtp.service.impl;

import com.example.smtp.dto.EmailRequestBody;
import com.example.smtp.dto.Person;
import com.example.smtp.exceptions.ClassConversionException;
import com.example.smtp.exceptions.MimeHelperException;
import com.example.smtp.exceptions.TemplateException;
import com.example.smtp.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendSimpleMessage(EmailRequestBody emailRequestBody) {
        sendMail(emailRequestBody, false, null, null);
    }

    @Override
    public void sendHtmlMessage(EmailRequestBody emailRequestBody) {
        sendMail(emailRequestBody, true, null, null);
    }

    @Override
    public void sendMessageWithAttachment(EmailRequestBody emailRequestBody, MultipartFile file) {
        sendMail(emailRequestBody, false, file, null);
    }

    @Override
    public void sendMessageWithInlineImage(EmailRequestBody emailRequestBody, MultipartFile image) {
        sendMail(emailRequestBody, true, null, image);
    }

    @Override
    public void sendTemplateMessage(EmailRequestBody emailRequestBody, Person person, String templateName) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            Context context = new Context();
            context.setVariables(getModelMap(person));
            String htmlContent = templateEngine.process(templateName, context);
            sendMailWithContent(emailRequestBody, htmlContent, true, message);
        } catch (TemplateException e) {
            throw new TemplateException(e.getMessage());
        } catch (MimeHelperException e) {
            throw new MimeHelperException(e.getMessage());
        }
    }

    private void sendMail(EmailRequestBody emailRequestBody, boolean isHtml, MultipartFile file, MultipartFile image) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailRequestBody.getTo());
            helper.setSubject(emailRequestBody.getSubject());
            if (isHtml) {
                helper.setText(emailRequestBody.getBody(), true);
            } else {
                helper.setText(emailRequestBody.getBody());
            }

            if (file != null)
                helper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), file);

            if (image != null)
                helper.addInline("image", convertMultipartFileToFile(image));

            mailSender.send(message);
        } catch (IOException e) {
            throw new ClassConversionException(e.getMessage());
        } catch (Exception e) {
            throw new MimeHelperException(e.getMessage());
        }
    }

    private void sendMailWithContent(EmailRequestBody emailRequestBody, String content, boolean isHtml, MimeMessage message) {
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailRequestBody.getTo());
            helper.setSubject(emailRequestBody.getSubject());
            helper.setText(content, isHtml);
            mailSender.send(message);
        } catch (Exception e) {
            throw new MimeHelperException(e.getMessage());
        }
    }

    private Map<String, Object> getModelMap(Person person) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("person", person);
        return modelMap;
    }

    public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        }
        return file;
    }

}
