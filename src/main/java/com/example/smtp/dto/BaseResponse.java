package com.example.smtp.dto;

import com.example.smtp.constants.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private ResponseStatus status;
    private String Message;
    private Integer statusCode;
    private HttpStatus statusType;

    public static BaseResponse success(String message) {
        BaseResponse response = new BaseResponse();
        response.setStatus(ResponseStatus.SUCCESS);
        response.setStatusCode(HttpStatus.OK.value());
        response.setStatusType(HttpStatus.OK);
        response.setMessage(Objects.requireNonNullElse(message, "Fetched response successfully"));
        return response;
    }

    public static BaseResponse failure(String message) {
        BaseResponse response = new BaseResponse();
        response.setStatus(ResponseStatus.FAILURE);
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setStatusType(HttpStatus.NOT_FOUND);
        response.setMessage(Objects.requireNonNullElse(message, "Unable to fetch response"));
        return response;
    }

}
