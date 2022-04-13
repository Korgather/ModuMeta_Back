package com.metaverse.station.back.web.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaverse.station.back.web.dto.PostsSaveRequestDto;
import com.nimbusds.oauth2.sdk.ErrorResponse;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;


@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomException handleCustomException(CustomException ce){
        return ce;
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Access denied", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({InsufficientAuthenticationException.class})
    public ResponseEntity<Object> handleInsufficientAuthentication(
            Exception ex, WebRequest request
    ){
        return new ResponseEntity<Object>(
                "로그인후 이용해주세요", new HttpHeaders(),HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidException(MethodArgumentNotValidException e, HttpServletRequest request) {

        ValidationErrorResponse errorResponse = ValidationErrorResponse.makeErrorResponse(e.getBindingResult());

        return new ResponseEntity<ValidationErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
