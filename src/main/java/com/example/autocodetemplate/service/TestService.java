package com.example.autocodetemplate.service;

import java.util.concurrent.Future;

public interface TestService {
    Future<String> doTaskOne() throws Exception;
    Future<String>  doTaskTwo() throws Exception;
    Future<String>  doTaskThree() throws Exception;
}
