package com.ticket.concert.exception;


import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ticket.concert.utils.ApiResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String MASKING_GENERAL_ERROR = "Oops!! Something Went Wrong";
    private static final String ERROR = "ERROR";
   
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> defaultErrorHandler(HttpServletRequest req, Exception e) {

        String urlInfo = req.getMethod() + " " + req.getRequestURL();
        log.error("endpoint :: {}", urlInfo);
        log.error("Caused by: " + e.getClass().getName());
        log.error(ERROR, e);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ApiResponse restResponseVO = new ApiResponse();
        restResponseVO.setSuccess(false);
        restResponseVO.setMessage(e.getMessage());
        // restResponseVO.setStatus(status);
        return new ResponseEntity<>(restResponseVO, status);
    }

    
}
