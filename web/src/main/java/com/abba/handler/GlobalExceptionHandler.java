package com.abba.handler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.NoResultException;


/**
 * @author dengbojing
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResultException.class)
    public @ResponseBody String noDataFoundException(NoResultException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody String otherException(Exception ex){
        return ex.getMessage();
    }
}
