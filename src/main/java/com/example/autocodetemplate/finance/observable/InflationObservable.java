package com.example.autocodetemplate.finance.observable;

import com.alibaba.fastjson.JSONObject;
import com.example.autocodetemplate.finance.ObserverMessage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Observable;

public class InflationObservable extends Observable {
    private Double rate;
    public InflationObservable() {

    }
    public InflationObservable(Double rate) {
        this.rate = rate;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate, Double curent) {
        this.rate = rate;
        setChanged();
        ObserverMessage mes = new ObserverMessage();
        mes.setType("通货膨胀率");
        mes.setMes("通货膨胀率：" + getRate());
        RateDataDTO dataDTO = new RateDataDTO(rate);
        BigDecimal subtract = new BigDecimal(rate + "").subtract(new BigDecimal(curent + ""));
        dataDTO.setTrend(subtract.compareTo(BigDecimal.valueOf(0)));
        mes.setData(JSONObject.toJSONString(dataDTO));
        mes.setTime(LocalDateTime.now().toString());
        this.notifyObservers(mes);
    }


    @Override
    public String toString() {
        return "current rate :" + getRate();
    }

}
