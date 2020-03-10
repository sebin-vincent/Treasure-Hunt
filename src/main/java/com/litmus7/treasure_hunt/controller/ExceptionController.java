package com.litmus7.treasure_hunt.controller;

import com.litmus7.treasure_hunt.dto.responsedto.ResponseInfo;
import com.litmus7.treasure_hunt.exception.AppException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class ExceptionController extends ResponseEntityExceptionHandler {

    private static final Logger logger= LogManager.getLogger(ExceptionController.class);

    @ExceptionHandler
    public final ResponseEntity<ResponseInfo> handleGeneralException(AppException ex, WebRequest request) {
        ResponseInfo responseInfo = new ResponseInfo(ex.getHttpStatus().value(), ex.getMessage());
        logger.error(ex.getMessage());
        return new ResponseEntity(responseInfo, ex.getHttpStatus());
    }

    @Override
    public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();

        ResponseInfo responseInfo = new ResponseInfo(400, ex.getMessage());

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        responseInfo.setPayload(errors);
        logger.error(ex.getMessage());
        return new ResponseEntity<>(responseInfo, HttpStatus.BAD_REQUEST);
    }




}
