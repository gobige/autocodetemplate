package com.example.autocodetemplate.finance;

import com.example.autocodetemplate.finance.enums.BusinessCycleEnum;
import com.example.autocodetemplate.finance.observable.CurrencySupplyObservable;
import com.example.autocodetemplate.finance.observable.EconomicObservable;
import com.example.autocodetemplate.finance.observer.BondObserver;
import com.example.autocodetemplate.finance.observer.InflationObserver;

public class finTest {
    public static void main(String[] args) {
//        EconomicObservable economic = new EconomicObservable(BusinessCycleEnum.EXPANSION);
//        BondObserver bondObserver = new BondObserver("债券需求/供给");
//        economic.addObserver(bondObserver);
//        economic.setCycle(BusinessCycleEnum.CONTRACTION);


        CurrencySupplyObservable currencySupply = new CurrencySupplyObservable();
        BondObserver bondObserver = new BondObserver("债券需求/供给");
        InflationObserver inflationObserver = new InflationObserver("通货膨胀率");

        currencySupply.addObserver(bondObserver);
        currencySupply.addObserver(inflationObserver);

        currencySupply.setSupply(2.1D, 1D);
    }
}
