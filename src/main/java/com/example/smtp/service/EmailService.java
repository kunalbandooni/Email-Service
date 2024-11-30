package com.example.smtp.service;

import com.example.smtp.dto.EmailRequestBody;
import com.example.smtp.dto.Person;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * Sends an email message using an HTML template.
     *
     * This method creates an email message by processing the specified template with the provided
     * {@code person} data. The resulting HTML content is then set as the body of the email and
     * sent using the configured {@code mailSender}.
     *
     * @param emailRequestBody The object containing the email details, including recipient address,
     *                         subject, and other email-specific metadata.
     * @param person The {@code Person} object containing the data to be used in the template.
     *               This data is passed to the template engine for dynamic content generation.
     * @param templateName The name of the template to be processed (usually the template file name
     *                     without the `.html` extension).
     * @throws TemplateException if there is an error processing the template.
     * @throws MimeHelperException if there is an error creating or sending the MIME message.
     */
    void sendTemplateMessage(EmailRequestBody emailRequestBody, Person person, String templateName);
}
