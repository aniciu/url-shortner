package com.demo.application;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.demo.domain.exception.ApplicationException;

@ControllerAdvice
public class LinkWebExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    void handleApplicationException(ApplicationException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // ToDo - Log error
        response.sendError(HttpStatus.SC_NO_CONTENT, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    void handleAllException(Exception ex, HttpServletRequest request, HttpServletResponse response) throws Exception{
        // ToDo - Log error
         response.sendError(HttpStatus.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}
