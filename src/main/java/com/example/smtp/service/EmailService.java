package com.example.smtp.service;

import com.example.smtp.dto.EmailRequestBody;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

public interface EmailService {
    /**
     * Sends a simple text email message.
     *
     * This method creates a simple email message using the provided details from
     * the {@code emailRequestBody} and sends it using the configured {@code mailSender}.
     *
     * @param emailRequestBody The object containing the email details including recipient address,
     *                         subject, and body text.
     * @throws MimeHelperException if there is an error sending the email.
     */
    void sendSimpleMessage(EmailRequestBody emailRequestBody);

    /**
     * Sends an HTML email message.
     *
     * This method creates an email message with HTML content using the provided details from
     * the {@code emailRequestBody}. It uses {@code MimeMessageHelper} to handle the creation
     * of the MIME message and sends it using the configured {@code mailSender}.
     *
     * @param emailRequestBody The object containing the email details including recipient address,
     *                         subject, and HTML body text.
     * @throws MimeHelperException if there is an error creating or sending the HTML email.
     */
    void sendHtmlMessage(EmailRequestBody emailRequestBody);

//    void sendMessageWithAttachment(EmailRequestBody emailRequestBody, MultipartFile file);
//    void sendMessageWithInlineImage(EmailRequestBody emailRequestBody, MultipartFile file);
//    void sendTemplateMessage(EmailRequestBody emailRequestBody, Map<String, Object> templateModel, String templateName);
}
