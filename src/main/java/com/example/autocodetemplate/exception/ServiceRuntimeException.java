package com.example.autocodetemplate.exception;


import org.slf4j.Logger;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class ServiceRuntimeException extends RuntimeException {


    /**
     *
     */
    private static final long serialVersionUID = 8010249704912029875L;

    public void logger(Logger logger) {
        logger.error("::::error::::exceptionCode【" + this.getExceptionCode() + "】::::exceptionDescription【" + this.getExceptionDescription() + "】");
        super.printStackTrace();
    }

    private Integer exceptionCode;
    private String exceptionDescription;

    public ServiceRuntimeException() {
        super();
    }

    public ServiceRuntimeException(String message) {
        super(message);
        this.exceptionDescription = message;
    }

    public ServiceRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceRuntimeException(Throwable cause) {
        super(cause);
    }

    public ServiceRuntimeException(Integer exceptionCode, String exceptionDescription) {
        super(exceptionDescription);
        setExceptionCode(exceptionCode);
        setExceptionDescription(exceptionDescription);
    }

    public ServiceRuntimeException(Integer exceptionCode, String exceptionDescription, Throwable cause) {
        super(cause);
        setExceptionCode(exceptionCode);
        setExceptionDescription(exceptionDescription);
    }

    public Integer getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(Integer exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionDescription() {
        return exceptionDescription;
    }

    public void setExceptionDescription(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }

}
