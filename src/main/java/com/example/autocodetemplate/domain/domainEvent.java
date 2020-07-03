package com.example.autocodetemplate.domain;

public class domainEvent<T> {
    public domainEvent(T data, String source) {
        this.data = data;
        this.source = source;
    }

    domainEvent() {}
    private String id;
    private long timestamp;
    private String source;
    private T data;
}
