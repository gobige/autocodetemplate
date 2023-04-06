package com.example.autocodetemplate.finance.observable;

import com.alibaba.fastjson.JSONObject;
import com.example.autocodetemplate.finance.ObserverMessage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Observable;

public class DeficitObservable extends Observable {
    private Double deficit;

    public DeficitObservable(Double deficit) {
        this.deficit = deficit;
    }

    public Double getDeficit() {
        return deficit;
    }

    public void setRate(Double rate, Double curent) {
        this.deficit = rate;
        setChanged();
        ObserverMessage mes = new ObserverMessage();
        mes.setType("财政赤字");
        mes.setMes("财政赤字：" + getDeficit());
        RateDataDTO dataDTO = new RateDataDTO(rate);
        BigDecimal subtract = new BigDecimal(rate + "").subtract(new BigDecimal(curent + ""));
        dataDTO.setTrend(subtract.compareTo(BigDecimal.valueOf(0)));
        mes.setData(JSONObject.toJSONString(dataDTO));
        mes.setTime(LocalDateTime.now().toString());
        this.notifyObservers(mes);
    }


    @Override
    public String toString() {
        return "current liquidity :" + getDeficit();
    }

}
