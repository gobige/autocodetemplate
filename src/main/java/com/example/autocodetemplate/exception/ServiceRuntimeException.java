package com.example.autocodetemplate.exception;


import org.slf4j.Logger;


public class ServiceRuntimeException extends Exception {

    private static final long serialVersionUID = 8010249704912029875L;

    public ServiceRuntimeException(String message){
        super(message);
    }
}
