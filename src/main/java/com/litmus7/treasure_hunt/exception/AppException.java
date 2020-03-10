package com.litmus7.treasure_hunt.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

    public AppException(){

    }

    public AppException(String msg,HttpStatus httpStatus){
        super(msg);
        this.httpStatus=httpStatus;

    }

    private HttpStatus httpStatus;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
