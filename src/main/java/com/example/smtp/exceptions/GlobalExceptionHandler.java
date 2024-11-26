package com.example.smtp.exceptions;

import com.example.smtp.constants.EmailMessages;
import com.example.smtp.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MimeHelperException.class)
    public @ResponseBody ResponseEntity<BaseResponse> handleMimeException(MimeHelperException e) {
        String errMsg = EmailMessages.MAIL_SENDING_FAILED + " Mime Helper Exception: " + e.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.failure(errMsg));
    }

    @ExceptionHandler(value = Exception.class)
    public @ResponseBody ResponseEntity<BaseResponse> handleException(Exception e) {
        String errMsg = "Some unknown error occurred: " + e.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.failure(errMsg));
    }

}
