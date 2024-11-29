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

    /**
     * Sends an email message with an attachment.
     *
     * This method creates an email message with the provided details from the {@code emailRequestBody}
     * and attaches the given file to the email. The email is sent using the configured {@code mailSender}.
     *
     * @param emailRequestBody The object containing the email details including recipient address,
     *                         subject, and body text.
     * @param file The attachment to be included in the email. This should be a valid {@code MultipartFile}.
     * @throws MimeHelperException if there is an error creating or sending the email with attachment.
     */
    void sendMessageWithAttachment(EmailRequestBody emailRequestBody, MultipartFile file);

    /**
     * Sends an email message with an inline image.
     *
     * This method creates an email message with the provided details from the {@code emailRequestBody},
     * and embeds the given image as an inline image within the HTML body of the email.
     * The email is sent using the configured {@code mailSender}.
     *
     * @param emailRequestBody The object containing the email details including recipient address,
     *                         subject, and body text.
     * @param image The image to be embedded inline in the email. This should be a valid {@code MultipartFile}.
     * @throws ClassConversionException if there is an error converting the image file.
     * @throws MimeHelperException if there is an error creating or sending the email with the inline image.
     */
    void sendMessageWithInlineImage(EmailRequestBody emailRequestBody, MultipartFile image);

//    void sendTemplateMessage(EmailRequestBody emailRequestBody, Map<String, Object> templateModel, String templateName);
}
