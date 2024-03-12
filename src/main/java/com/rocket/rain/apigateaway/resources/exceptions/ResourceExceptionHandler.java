package com.rocket.rain.apigateaway.resources.exceptions;

import com.rocket.rain.apigateaway.services.exceptions.DatabaseException;
import com.rocket.rain.apigateaway.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice//intercepta as excecao para que o obj possa executar algum tipo de tratamento
public class ResourceExceptionHandler{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> searchNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not Found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(java.time.Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity dataBase(DatabaseException e, HttpServletRequest request){
        String error = "DataBase error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<StandardError> noresourceFound(NoResourceFoundException e, HttpServletRequest request){
        String error = "Controller not Found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(java.time.Instant.now(),status.value(),error,e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodarugumentNotValid(MethodArgumentNotValidException e,
                                                                 HttpServletRequest request){
        String error = "Arguments Missed";
        HttpStatus status = HttpStatus.NOT_FOUND;
        System.out.println(e.getFieldErrors().toString());
        StandardError err = new StandardError(java.time.Instant.now(),status.value(),error,
               extractFieldsWithError(e.getFieldErrors().toString()),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    public static String extractFieldsWithError(String errorMessage) {
        //reponsael por filtrar a mensagem de erro retornada pela exception ArgumentNotValid
        List<String> fieldNames = new ArrayList<>();
        Pattern pattern = Pattern.compile("on field '(.*?)'");
        Matcher matcher = pattern.matcher(errorMessage);

        while (matcher.find()) {
            fieldNames.add(matcher.group(1));
        }
        return String.join(", ", fieldNames);
    }
}
