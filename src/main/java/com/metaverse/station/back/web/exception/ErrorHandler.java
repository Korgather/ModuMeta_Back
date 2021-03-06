package com.metaverse.station.back.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

@Slf4j
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
    public ResponseEntity<ValidationErrorResponse> handleValidException(MethodArgumentNotValidException e, WebRequest request) {

        ValidationErrorResponse errorResponse = ValidationErrorResponse.makeErrorResponse(e.getBindingResult());

        return new ResponseEntity<ValidationErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileSizeLimitExceededException.class)
    public  ResponseEntity<Object> handleFileSizeLimitException(FileSizeLimitExceededException ex, WebRequest request){

        return ResponseEntity.badRequest().body("{ \"error\": \"" + "5메가 이하의 파일을 업로드해주세요." + "\" }");

    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(
            Exception ex, HttpServletRequest request
    ) {

        log.info(ex.getMessage() +": " + request.getRequestURI());
        return new ResponseEntity<Object>("잘못된 요청입니다.",new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
