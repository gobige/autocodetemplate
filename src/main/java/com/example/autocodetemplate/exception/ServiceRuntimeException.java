package com.example.autocodetemplate.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义异常
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ServiceRuntimeException extends Exception {

    private static final long serialVersionUID = 8010249704912029875L;

    public ServiceRuntimeException(String message){
        super(message);
    }
}
