package com.example.autocodetemplate.finance.observable;

import com.alibaba.fastjson.JSONObject;
import com.example.autocodetemplate.finance.ObserverMessage;
import com.example.autocodetemplate.finance.enums.BusinessCycleEnum;

import java.time.LocalDateTime;
import java.util.Observable;

public class EconomicObservable extends Observable {
    private BusinessCycleEnum cycle;

    public EconomicObservable(BusinessCycleEnum cycle) {
        this.cycle = cycle;
    }

    public BusinessCycleEnum getCycle() {
        return cycle;
    }

    public void setCycle(BusinessCycleEnum cycle) {
        this.cycle = cycle;
        setChanged();
        ObserverMessage mes = new ObserverMessage();
        mes.setType("economic");
        mes.setMes("当前经济周期：" + getCycle().getValue() + "表现" + getCycle().getDesc());
        mes.setData(JSONObject.toJSONString(new EconomicDataDTO(cycle.getKey())));
        mes.setTime(LocalDateTime.now().toString());
        this.notifyObservers(mes);
    }


    @Override
    public String toString() {
        return "current cycle :" + getCycle();
    }

}
