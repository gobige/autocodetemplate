package com.example.autocodetemplate.handler;

import com.example.autocodetemplate.exception.ErrorInfo;
import com.example.autocodetemplate.exception.ServiceRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理器，处理异常返回Handler
 */
@ControllerAdvice
public class ServiceRuntimeExceptionHandler {

    @ExceptionHandler(value = ServiceRuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInfo<String> defaultExceptionHandler(HttpServletRequest request, ServiceRuntimeException exception) {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(exception.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("data");
        r.setUrl(request.getRequestURL().toString());
        return r;
    }
}
