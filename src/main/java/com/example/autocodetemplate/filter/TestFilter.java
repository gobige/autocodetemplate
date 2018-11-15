package com.example.autocodetemplate.filter;

import com.example.autocodetemplate.config.CustomJsonDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
public class TestFilter implements Serializable {
    private static final long serialVersionUID = 8101292251336919990L;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date startTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
