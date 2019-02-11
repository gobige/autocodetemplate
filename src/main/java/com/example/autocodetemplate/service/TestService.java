package com.example.autocodetemplate.service;

import com.example.autocodetemplate.domain.SysAppVersionInfo;
import com.example.autocodetemplate.exception.ServiceRuntimeException;

import java.util.concurrent.Future;

public interface TestService {
    Future<String> doTaskOne() throws Exception;
    Future<String>  doTaskTwo() throws Exception;
    Future<String>  doTaskThree() throws Exception;

    SysAppVersionInfo testGetSql() throws Exception;;
}
