package com.example.autocodetemplate.aspect;

import com.example.autocodetemplate.AbstractTestInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class TestInterfaceImpl extends AbstractTestInterface {

    @Override
    public void handle() {
        System.out.println("TestInterfaceImpl handle");
        doSomeThing();

    }
}
