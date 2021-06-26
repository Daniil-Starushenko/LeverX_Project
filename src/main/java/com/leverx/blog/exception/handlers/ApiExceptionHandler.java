package com.leverx.blog.exception.handlers;

import com.leverx.blog.exception.ApiException;
import com.leverx.blog.exception.entity.EntityNotFoundException;
import com.leverx.blog.model.dto.ApiExceptionDto;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ApiExceptionDto handleEntityNotFoundException(ApiException ex) {
        return ApiExceptionDto.of(ex);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionDto apiErrorDto = ApiExceptionDto.builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .message(ex.getLocalizedMessage())
                .build();
        return buildResponseEntity(apiErrorDto);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionDto apiErrorDto =ApiExceptionDto .builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .message(ex.getLocalizedMessage())
                .build();
        return buildResponseEntity(apiErrorDto);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionDto apiErrorDto =ApiExceptionDto .builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .message(ex.getLocalizedMessage())
                .build();
        return buildResponseEntity(apiErrorDto);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionDto apiErrorDto =ApiExceptionDto .builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .message(ex.getLocalizedMessage())
                .build();
        return buildResponseEntity(apiErrorDto);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionDto apiErrorDto =ApiExceptionDto .builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .message(ex.getLocalizedMessage())
                .build();
        return buildResponseEntity(apiErrorDto);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionDto apiErrorDto =ApiExceptionDto .builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .message(ex.getLocalizedMessage())
                .build();
        return buildResponseEntity(apiErrorDto);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionDto apiErrorDto =ApiExceptionDto .builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .message(ex.getLocalizedMessage())
                .build();
        return buildResponseEntity(apiErrorDto);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionDto apiErrorDto =ApiExceptionDto .builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .message(ex.getLocalizedMessage())
                .build();
        return buildResponseEntity(apiErrorDto);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionDto apiErrorDto =ApiExceptionDto .builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .message(ex.getLocalizedMessage())
                .build();
        return buildResponseEntity(apiErrorDto);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionDto apiErrorDto =ApiExceptionDto .builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .message(ex.getLocalizedMessage())
                .build();
        return buildResponseEntity(apiErrorDto);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionDto apiErrorDto =ApiExceptionDto .builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .message(ex.getLocalizedMessage())
                .build();
        return buildResponseEntity(apiErrorDto);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionDto apiErrorDto = ApiExceptionDto .builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .message(ex.getLocalizedMessage())
                .build();
        return buildResponseEntity(apiErrorDto);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionDto apiErrorDto = ApiExceptionDto .builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .message(ex.getLocalizedMessage())
                .build();
        return buildResponseEntity(apiErrorDto);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        ApiExceptionDto apiErrorDto = ApiExceptionDto .builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .message(ex.getLocalizedMessage())
                .build();
        return buildResponseEntity(apiErrorDto);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiExceptionDto  apiErrorDto) {
        return ResponseEntity.status(apiErrorDto.getStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiErrorDto);
    }
}
