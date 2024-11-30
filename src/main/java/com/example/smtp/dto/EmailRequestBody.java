package com.example.smtp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequestBody {
    private String[] to;
    private String subject;
    private String body;

    public void setTo(String to) {
        this.to = to.split(",");
    }

    public EmailRequestBody(String to, String subject) {
        setTo(to);
        setSubject(subject);
    }
}
