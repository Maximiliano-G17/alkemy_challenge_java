package com.application.web.university.controller;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
	
	private Log logger = LogFactory.getLog(ExceptionController.class);
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String error404(HttpServletRequest req, Exception e){
		logger.error("Request: " + req.getRequestURL() + " raised " + e);
        return "views/error404";
    }
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public String error400(HttpServletRequest req, Exception e){
		logger.error("Request: " + req.getRequestURL() + " raised " + e);
        return "views/error400";
    }
}