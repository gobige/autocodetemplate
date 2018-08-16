package com.example.autocodetemplate.handler;

import com.example.autocodetemplate.exception.ErrorInfo;
import com.example.autocodetemplate.exception.ServiceRuntimeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@ControllerAdvice
public class ServiceRuntimeExceptionHandler {

    @ExceptionHandler(value = ServiceRuntimeException.class)
    @ResponseBody
    public ErrorInfo<String> defaultExceptionHandler(HttpServletRequest request, ServiceRuntimeException exception) {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(exception.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("data");
        r.setUrl(request.getRequestURL().toString());
        return r;
    }
}
