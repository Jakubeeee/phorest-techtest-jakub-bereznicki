package com.jakubeeee.misc;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
final class ConstraintViolationHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value());
    }
}
