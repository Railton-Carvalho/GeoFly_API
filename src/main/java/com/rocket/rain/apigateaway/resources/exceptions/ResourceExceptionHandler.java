package com.rocket.rain.apigateaway.resources.exceptions;

import com.rocket.rain.apigateaway.services.exceptions.DatabaseException;
import com.rocket.rain.apigateaway.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice//intercepta as excecao para que o obj possa executar algum tipo de tratamento
public class ResourceExceptionHandler{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> searchNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not Found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        System.out.println(request.getMethod());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity dataBase(DatabaseException e, HttpServletRequest request){
        String error = "DataBase error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        System.out.println(request.getMethod());
        return ResponseEntity.status(status).body(err);
    }
}
