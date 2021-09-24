package com.srum.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityNotFoundException;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 13/09/2021
 */
@ControllerAdvice
public class ExceptionManagement implements WebMvcConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionManagement.class);

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public String handlerBadRequest(Exception e) {
        LOGGER.error(e.getMessage());
        return "error/400";
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class, EntityNotFoundException.class})
    public String handleError404(Exception e) {
        LOGGER.error(e.getMessage());
        return "error/404";
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public String handlerErrorServer(Exception e) {
        LOGGER.error(e.getMessage());
        return "error/500";
    }

    @ExceptionHandler(value = {Exception.class})
    public String handlerAllException(Exception e) {
        LOGGER.error(e.getMessage());
        return "error/error";
    }
}
