package com.example.autocodetemplate.finance.observer;

import com.alibaba.fastjson.JSON;
import com.example.autocodetemplate.finance.ObserverMessage;
import com.example.autocodetemplate.finance.enums.BusinessCycleEnum;
import com.example.autocodetemplate.finance.observable.*;

import java.util.Observable;
import java.util.Observer;

public class CurrencyObserver implements Observer {
    private String name;

    public CurrencyObserver(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update(Observable o, Object arg) {
        System.out.println(getName() + "受到影响：");

        if (o instanceof IncomeObservable) {
            incomeHandle((ObserverMessage) arg);
        } else if (o instanceof PriceIndexObservable) {
            priceIndexHandle((ObserverMessage) arg);
        }
    }

    private void priceIndexHandle(ObserverMessage arg) {
        ObserverMessage mes = arg;

        RateDataDTO dataDTO = JSON.toJavaObject(JSON.parseObject(mes.getData()), RateDataDTO.class);
        if (dataDTO == null || dataDTO.getRate() == null) {
            System.out.println("无法识别居民价格指数" + dataDTO);
            return;
        }

        switch (dataDTO.getTrend()) {
            case 1:
                System.out.println("流动性理论--居民物价指数【上涨】，货币需求增加，货币需求曲线右移，利率上升");
                break;
            case -1:
                System.out.println("流动性理论--居民物价指数【下降】，货币需求减少，货币需求曲线左移，利率下降");
                break;
            case 0:
                System.out.println("流动性理论--居民物价指数不变 货币需求均衡");
                break;
            default:
                break;
        }
    }
    private void incomeHandle(ObserverMessage arg) {
        ObserverMessage mes = arg;

        RateDataDTO dataDTO = JSON.toJavaObject(JSON.parseObject(mes.getData()), RateDataDTO.class);
        if (dataDTO == null || dataDTO.getRate() == null) {
            System.out.println("无法识别居民收入指数" + dataDTO);
            return;
        }

        switch (dataDTO.getTrend()) {
            case 1:
                System.out.println("流动性理论--居民收入指数【上涨】，货币需求增加，货币需求曲线右移，利率上升");
                break;
            case -1:
                System.out.println("流动性理论--居民收入指数【下降】，货币需求减少，货币需求曲线左移，利率下降");
                break;
            case 0:
                System.out.println("流动性理论--居民收入指数不变 货币需求均衡");
                break;
            default:
                break;
        }
    }

}
