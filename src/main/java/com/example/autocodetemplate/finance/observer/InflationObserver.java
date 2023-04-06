package com.example.autocodetemplate.finance.observer;

import com.alibaba.fastjson.JSON;
import com.example.autocodetemplate.finance.ObserverMessage;
import com.example.autocodetemplate.finance.observable.CurrencySupplyObservable;
import com.example.autocodetemplate.finance.observable.InflationObservable;
import com.example.autocodetemplate.finance.observable.RateDataDTO;

import java.util.Observable;
import java.util.Observer;

public class InflationObserver implements Observer {
    private String name;

    public InflationObserver(String name) {
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

        if (o instanceof CurrencySupplyObservable) {
            curSupplyHandle((ObserverMessage) arg);
        }
    }

    private void curSupplyHandle(ObserverMessage arg) {
        ObserverMessage mes = arg;

        RateDataDTO dataDTO = JSON.toJavaObject(JSON.parseObject(mes.getData()), RateDataDTO.class);
        if (dataDTO == null || dataDTO.getRate() == null) {
            System.out.println("无法识别货币供给趋势" + dataDTO);
            return;
        }

        InflationObservable inflationObservable = new InflationObservable();
        inflationObservable.addObserver(new BondObserver("债券需求/供给"));
        switch (dataDTO.getTrend()) {
            case 1:
                System.out.println("流动性理论--货币供给【宽松】，流动性效应立即见效，利率下降，预期通货膨胀率上升");

                inflationObservable.setRate(2D, 1D);
                break;
            case -1:
                System.out.println("流动性理论--货币供给【紧缩】，流动性效应立即见效，利率上升，预期通货膨胀率下降");

                inflationObservable.setRate(1D, 2D);
                break;
            case 0:
                System.out.println("流动性理论--货币供给不变 预期通货膨胀率不变");

                inflationObservable.setRate(1D, 1D);
                break;
            default:
                break;
        }
    }

}
