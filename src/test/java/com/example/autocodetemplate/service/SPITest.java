package com.example.autocodetemplate.service;

import com.example.autocodetemplate.ohter.practice.spi.Developer;
import org.junit.Test;

import java.util.Objects;
import java.util.ServiceLoader;

public class SPITest {

    @Test
    public void testSPI() {
        Objects.requireNonNull(null, "Service interface cannot be null");

        ServiceLoader<Developer> serviceLoader = ServiceLoader.load(Developer.class);
        serviceLoader.forEach(Developer::sayHi);

    }
}
