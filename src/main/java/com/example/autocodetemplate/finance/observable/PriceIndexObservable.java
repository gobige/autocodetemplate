package com.example.autocodetemplate.finance.observable;

import com.alibaba.fastjson.JSONObject;
import com.example.autocodetemplate.finance.ObserverMessage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Observable;

public class PriceIndexObservable extends Observable {
    private Double priceLevel;

    public PriceIndexObservable(Double priceLevel) {
        this.priceLevel = priceLevel;
    }

    public Double getPriceLevel() {
        return priceLevel;
    }

    public void setIncome(Double priceLevel, Double curent) {
        this.priceLevel = priceLevel;
        setChanged();
        ObserverMessage mes = new ObserverMessage();
        mes.setType("居民价格指数");
        mes.setMes("居民价格指数：" + getPriceLevel());
        RateDataDTO dataDTO = new RateDataDTO(priceLevel);
        BigDecimal subtract = new BigDecimal(curent + "").subtract(new BigDecimal(priceLevel + ""));
        dataDTO.setTrend(subtract.compareTo(BigDecimal.valueOf(0)));
        mes.setData(JSONObject.toJSONString(dataDTO));
        mes.setTime(LocalDateTime.now().toString());
        this.notifyObservers(mes);
    }


    @Override
    public String toString() {
        return "current priceLevel :" + getPriceLevel();
    }

}
