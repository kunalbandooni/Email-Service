package com.example.smtp.controller;

import com.example.smtp.constants.EmailMessages;
import com.example.smtp.dto.BaseResponse;
import com.example.smtp.dto.EmailRequestBody;
import com.example.smtp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/health")
    private ResponseEntity<BaseResponse> checkHealth() {
        return ResponseEntity.ok(BaseResponse.success("Server alive..."));
    }

}
