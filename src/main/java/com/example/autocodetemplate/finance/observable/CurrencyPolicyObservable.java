package com.example.autocodetemplate.finance.observable;

import com.alibaba.fastjson.JSONObject;
import com.example.autocodetemplate.finance.ObserverMessage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Observable;

public class CurrencyPolicyObservable extends Observable {
    private Double currencyPolicy;

    public CurrencyPolicyObservable(Double currencyPolicy) {
        this.currencyPolicy = currencyPolicy;
    }

    public Double getCurrencyPolicy() {
        return currencyPolicy;
    }

    public void setRate(Double rate, Double curent) {
        this.currencyPolicy = rate;
        setChanged();
        ObserverMessage mes = new ObserverMessage();
        mes.setType("债券买卖货币政策");
        mes.setMes("债券买卖货币政策：" + getCurrencyPolicy());
        RateDataDTO dataDTO = new RateDataDTO(rate);
        BigDecimal subtract = new BigDecimal(rate + "").subtract(new BigDecimal(curent + ""));
        dataDTO.setTrend(subtract.compareTo(BigDecimal.valueOf(0)));
        mes.setData(JSONObject.toJSONString(dataDTO));
        mes.setTime(LocalDateTime.now().toString());
        this.notifyObservers(mes);
    }


    @Override
    public String toString() {
        return "current liquidity :" + getCurrencyPolicy();
    }

}
