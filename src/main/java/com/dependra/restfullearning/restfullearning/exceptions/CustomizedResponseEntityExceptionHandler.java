package com.dependra.restfullearning.restfullearning.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request)
            throws Exception {
        ExceptionDetail exceptionDetail = new ExceptionDetail(new Date(),
                request.getDescription(false), ex.getMessage());
        return new ResponseEntity(exceptionDetail, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CourseNotFoundException.class)
    public final ResponseEntity<Object> handleCourseNotFoundException(Exception ex, WebRequest request)
            throws Exception {
        ExceptionDetail exceptionDetail = new ExceptionDetail(new Date(),
                request.getDescription(false), ex.getMessage());
        return new ResponseEntity(exceptionDetail, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionDetail exceptionDetail=new ExceptionDetail(new Date(),ex.getBindingResult().toString(),
                ex.getMessage());
        return  new ResponseEntity(exceptionDetail,HttpStatus.BAD_REQUEST);
    }
}
