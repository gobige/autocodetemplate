package com.example.autocodetemplate.finance.observable;

import com.alibaba.fastjson.JSONObject;
import com.example.autocodetemplate.finance.ObserverMessage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Observable;

public class IncomeObservable extends Observable {
    private Double income;

    public IncomeObservable(Double income) {
        this.income = income;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double rate, Double curent) {
        this.income = rate;
        setChanged();
        ObserverMessage mes = new ObserverMessage();
        mes.setType("居民收入指数");
        mes.setMes("收入指数：" + getIncome());
        RateDataDTO dataDTO = new RateDataDTO(rate);
        BigDecimal subtract = new BigDecimal(rate + "").subtract(new BigDecimal(curent + ""));
        dataDTO.setTrend(subtract.compareTo(BigDecimal.valueOf(0)));
        mes.setData(JSONObject.toJSONString(dataDTO));
        mes.setTime(LocalDateTime.now().toString());
        this.notifyObservers(mes);
    }


    @Override
    public String toString() {
        return "current income :" + getIncome();
    }

}
