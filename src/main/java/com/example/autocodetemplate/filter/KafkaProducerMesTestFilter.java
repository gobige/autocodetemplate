package com.example.autocodetemplate.filter;

import java.io.Serializable;

public class KafkaProducerMesTestFilter implements Serializable {

    private static final long serialVersionUID = 5221329282798030651L;

    private String mes;

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
