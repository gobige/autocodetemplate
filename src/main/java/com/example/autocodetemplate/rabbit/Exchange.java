package com.example.autocodetemplate.rabbit;

public enum Exchange {

    USER_TOPIC_EXCHANGE("USER_TOPIC_EXCHANGE", "用户相关的消息主题");

    private String key;
    private String name;

    private Exchange(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
