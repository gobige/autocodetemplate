package com.example.autocodetemplate.finance.observable;

import com.alibaba.fastjson.JSONObject;
import com.example.autocodetemplate.finance.ObserverMessage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Observable;

public class BondLiquidityObservable extends Observable {
    private Double liquidity;

    public BondLiquidityObservable(Double liquidity) {
        this.liquidity = liquidity;
    }

    public Double getLiquidity() {
        return liquidity;
    }

    public void setRate(Double rate, Double curent) {
        this.liquidity = rate;
        setChanged();
        ObserverMessage mes = new ObserverMessage();
        mes.setType("流动性");
        mes.setMes("流动性：" + getLiquidity());
        RateDataDTO dataDTO = new RateDataDTO(rate);
        BigDecimal subtract = new BigDecimal(rate + "").subtract(new BigDecimal(curent + ""));
        dataDTO.setTrend(subtract.compareTo(BigDecimal.valueOf(0)));
        mes.setData(JSONObject.toJSONString(dataDTO));
        mes.setTime(LocalDateTime.now().toString());
        this.notifyObservers(mes);
    }


    @Override
    public String toString() {
        return "current liquidity :" + getLiquidity();
    }

}
