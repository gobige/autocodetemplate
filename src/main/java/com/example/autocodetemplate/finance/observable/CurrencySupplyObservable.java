package com.example.autocodetemplate.finance.observable;

import com.alibaba.fastjson.JSONObject;
import com.example.autocodetemplate.finance.ObserverMessage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Observable;

public class CurrencySupplyObservable extends Observable {
    private Double supply;

    public CurrencySupplyObservable() {
    }
    public CurrencySupplyObservable(Double supply) {
        this.supply = supply;
    }

    public Double getSupply() {
        return supply;
    }

    public void setSupply(Double rate, Double curent) {
        this.supply = rate;
        setChanged();
        ObserverMessage mes = new ObserverMessage();
        mes.setType("流动性");
        mes.setMes("流动性：" + getSupply());
        RateDataDTO dataDTO = new RateDataDTO(rate);
        BigDecimal subtract = new BigDecimal(rate + "").subtract(new BigDecimal(curent + ""));
        dataDTO.setTrend(subtract.compareTo(BigDecimal.valueOf(0)));
        mes.setData(JSONObject.toJSONString(dataDTO));
        mes.setTime(LocalDateTime.now().toString());
        this.notifyObservers(mes);
    }


    @Override
    public String toString() {
        return "current currency supply :" + getSupply();
    }

}
