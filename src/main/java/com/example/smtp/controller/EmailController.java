package com.example.smtp.controller;

import com.example.smtp.constants.EmailMessages;
import com.example.smtp.dto.BaseResponse;
import com.example.smtp.dto.EmailRequestBody;
import com.example.smtp.dto.Person;
import com.example.smtp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/simple-mail")
    public ResponseEntity<BaseResponse> sendEmail(@RequestBody EmailRequestBody emailRequestBody) {
        emailService.sendSimpleMessage(emailRequestBody);
        return ResponseEntity.ok(BaseResponse.success(EmailMessages.MAIL_SENT_SUCCESS));
    }

    @PostMapping("/html-mail")
    public ResponseEntity<BaseResponse> sendHtmlEmail(@RequestBody EmailRequestBody emailRequestBody) {
        emailService.sendHtmlMessage(emailRequestBody);
        return ResponseEntity.ok(BaseResponse.success(EmailMessages.MAIL_SENT_SUCCESS));
    }

    @PostMapping("/attachment-mail")
    public ResponseEntity<BaseResponse> sendEmailWithAttachment(@RequestPart("file") MultipartFile attachment,
                                                                @RequestPart("email-body") EmailRequestBody emailRequestBody) {
        emailService.sendMessageWithAttachment(emailRequestBody, attachment);
        return ResponseEntity.ok(BaseResponse.success(EmailMessages.MAIL_SENT_SUCCESS));
    }

    @PostMapping("/inline-image-mail")
    public ResponseEntity<BaseResponse> sendEmailWithInlineImage(@RequestPart("img") MultipartFile image,
                                                                 @RequestPart("email-body") EmailRequestBody emailRequestBody) {
        emailService.sendMessageWithInlineImage(emailRequestBody, image);
        return ResponseEntity.ok(BaseResponse.success(EmailMessages.MAIL_SENT_SUCCESS));
    }

    @PostMapping("/template-mail")
    public ResponseEntity<BaseResponse> sendTemplateEmail(@RequestParam("template-name") String templateName,
                                                          @RequestParam("to") String to,
                                                          @RequestParam("subject") String subject,
                                                          @RequestBody Person person) {
        EmailRequestBody emailRequestBody = new EmailRequestBody(to, subject);
        emailService.sendTemplateMessage(emailRequestBody, person, templateName);
        return ResponseEntity.ok(BaseResponse.success(EmailMessages.MAIL_SENT_SUCCESS));
    }

    @GetMapping("/health")
    private ResponseEntity<BaseResponse> checkHealth() {
        return ResponseEntity.ok(BaseResponse.success("Server alive..."));
    }

}
