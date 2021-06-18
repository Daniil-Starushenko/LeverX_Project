package com.leverx.blog.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leverx.blog.exception.ApiException;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiExceptionDto {

    private HttpStatus status;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.YYYY hh:mm:ss")
    private LocalDateTime timestamp;

    public static ApiExceptionDto of(ApiException ex) {
        return ApiExceptionDto.builder()
                .status(ex.getStatus())
                .message(ex.getMessage())
                .timestamp(ex.getTimestamp())
                .build();
    }

}
