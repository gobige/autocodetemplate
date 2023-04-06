package com.example.autocodetemplate.finance.observer;

import com.alibaba.fastjson.JSON;
import com.example.autocodetemplate.finance.ObserverMessage;
import com.example.autocodetemplate.finance.enums.BusinessCycleEnum;
import com.example.autocodetemplate.finance.enums.TrendEnum;
import com.example.autocodetemplate.finance.observable.*;

import java.util.Observable;
import java.util.Observer;

public class BondObserver implements Observer {
    private String name;

    public BondObserver(String name) {
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

        if (o instanceof EconomicObservable) {
            ecoHandle((ObserverMessage) arg);
        } else if (o instanceof RateObservable) {
            rateHandle((ObserverMessage) arg);
        } else if (o instanceof OtherReturnObservable) {
            otherReturnHandle((ObserverMessage) arg);
        } else if (o instanceof InflationObservable) {
            inflationHandle((ObserverMessage) arg);
        } else if (o instanceof RiskObservable) {
            riskHandle((ObserverMessage) arg);
        } else if (o instanceof BondLiquidityObservable) {
            liquidityHandle((ObserverMessage) arg);
        } else if (o instanceof DeficitObservable) {
            deficitHandle((ObserverMessage) arg);
        } else if (o instanceof CurrencyPolicyObservable) {
            curPoliceHandle((ObserverMessage) arg);
        } else if (o instanceof CurrencySupplyObservable) {
            curSupplyHandle((ObserverMessage) arg);
        }
    }

    private void curSupplyHandle(ObserverMessage arg) {
        ObserverMessage mes = arg;

        RateDataDTO dataDTO = JSON.toJavaObject(JSON.parseObject(mes.getData()), RateDataDTO.class);
        if (dataDTO == null || dataDTO.getRate() == null) {
            System.out.println("无法识别货币供给" + dataDTO);
            return;
        }

        switch (dataDTO.getTrend()) {
            case 1:
                System.out.println("流动性理论--货币供给【宽松】，利率下降，债券需求增加，债券价格上升");
                break;
            case -1:
                System.out.println("流动性理论--货币供给【紧缩】，利率上升，债券需求减少，债券价格下降");
                break;
            case 0:
                System.out.println("流动性理论--货币供给不变 债券需求均衡");
                break;
            default:
                break;
        }
    }

    private void curPoliceHandle(ObserverMessage arg) {
        ObserverMessage mes = arg;

        RateDataDTO dataDTO = JSON.toJavaObject(JSON.parseObject(mes.getData()), RateDataDTO.class);
        if (dataDTO == null || dataDTO.getRate() == null) {
            System.out.println("无法识别债券买卖货币政策" + dataDTO);
            return;
        }

        switch (dataDTO.getTrend()) {
            case 1:
                System.out.println("债券需求理论--债券买卖货币政策【宽松】，供给增加，供给曲线右移");
                break;
            case -1:
                System.out.println("债券需求理论--债券买卖货币政策【严格】，供给减少，供给曲线左移");
                break;
            case 0:
                System.out.println("债券需求理论--债券买卖货币政策不变 债券供给均衡");
                break;
            default:
                break;
        }
    }
    private void deficitHandle(ObserverMessage arg) {
        ObserverMessage mes = arg;

        RateDataDTO dataDTO = JSON.toJavaObject(JSON.parseObject(mes.getData()), RateDataDTO.class);
        if (dataDTO == null || dataDTO.getRate() == null) {
            System.out.println("无法识别流动性" + dataDTO);
            return;
        }

        switch (dataDTO.getTrend()) {
            case 1:
                System.out.println("债券需求理论--预期财政赤字【高】，供给增加，供给曲线右移");
                break;
            case -1:
                System.out.println("债券需求理论--预期财政赤字【低】，供给减少，供给曲线左移");
                break;
            case 0:
                System.out.println("债券需求理论--预期财政赤字不变 债券供给均衡");
                break;
            default:
                break;
        }
    }

    private void liquidityHandle(ObserverMessage arg) {
        ObserverMessage mes = arg;

        RateDataDTO dataDTO = JSON.toJavaObject(JSON.parseObject(mes.getData()), RateDataDTO.class);
        if (dataDTO == null || dataDTO.getRate() == null) {
            System.out.println("无法识别流动性" + dataDTO);
            return;
        }

        switch (dataDTO.getTrend()) {
            case 1:
                System.out.println("债券需求理论--流动性【高】，需求增加，需求曲线右移");
                break;
            case -1:
                System.out.println("债券需求理论--流动性【低】，需求减少，需求曲线左移");
                break;
            case 0:
                System.out.println("债券需求理论--流动性不变 债券需求均衡");
                break;
            default:
                break;
        }
    }

    private void inflationHandle(ObserverMessage arg) {
        ObserverMessage mes = arg;

        RateDataDTO dataDTO = JSON.toJavaObject(JSON.parseObject(mes.getData()), RateDataDTO.class);
        if (dataDTO == null || dataDTO.getRate() == null) {
            System.out.println("无法识别利率" + dataDTO);
            return;
        }

        switch (dataDTO.getTrend()) {
            case 1:
                System.out.println("债券需求理论--预期通货膨胀率【高】，真实利率(借款成本)低，债券供给增加，供给曲线右移；需求减少，需求曲线左移");
                break;
            case -1:
                System.out.println("债券需求理论--预期通货膨胀率【低】，真实利率(借款成本)高，债券供给减少，供给曲线左移；需求增加，需求曲线右移");
                break;
            case 0:
                System.out.println("债券需求理论--预期通货膨胀率不变 债券需求均衡");
                break;
            default:
                break;
        }
    }

    private void riskHandle(ObserverMessage arg) {
        ObserverMessage mes = arg;

        RateDataDTO dataDTO = JSON.toJavaObject(JSON.parseObject(mes.getData()), RateDataDTO.class);
        if (dataDTO == null || dataDTO.getRate() == null) {
            System.out.println("无法识别风险利率" + dataDTO);
            return;
        }

        switch (dataDTO.getTrend()) {
            case 1:
                System.out.println("债券需求理论--观察到风险【高】，债券需求减少， 需求曲线左移，债券价格下降，收益率上升");
                break;
            case -1:
                System.out.println("债券需求理论--观察到风险【低】，债券需求增加， 需求曲线右移，债券价格上升，收益率下降");
                break;
            case 0:
                System.out.println("债券需求理论--观察到风险不变 债券需求均衡");
                break;
            default:
                break;
        }
    }

    private void otherReturnHandle(ObserverMessage arg) {
        ObserverMessage mes = arg;

        RateDataDTO dataDTO = JSON.toJavaObject(JSON.parseObject(mes.getData()), RateDataDTO.class);
        if (dataDTO == null || dataDTO.getRate() == null) {
            System.out.println("无法识别利率" + dataDTO);
            return;
        }

        switch (dataDTO.getTrend()) {
            case 1:
                System.out.println("债券需求理论--其他资产回报利率【高】，债券需求减少，需求曲线左移；");
                break;
            case -1:
                System.out.println("债券需求理论--其他资产回报利率【低】，债券需求增加，需求曲线右移；");
                break;
            case 0:
                System.out.println("债券需求理论--其他资产回报利率不变 债券需求均衡");
                break;
            default:
                break;
        }
    }

    private void rateHandle(ObserverMessage arg) {
        ObserverMessage mes = arg;

        RateDataDTO dataDTO = JSON.toJavaObject(JSON.parseObject(mes.getData()), RateDataDTO.class);
        if (dataDTO == null || dataDTO.getRate() == null) {
            System.out.println("无法识别经济周期" + dataDTO);
            return;
        }

        switch (dataDTO.getTrend()) {
            case 1:
                System.out.println("债券需求理论--未来利率【高】使得长期债券预期回报率降低，债券需求减少，需求曲线左移；");
                break;
            case -1:
                System.out.println("债券需求理论--未来利率【低】使得长期债券预期回报率增高，债券需求增加，需求曲线右移；");
                break;
            case 0:
                System.out.println("债券需求理论--未来利率不变 债券需求均衡");
                break;
            default:
                break;
        }
    }

    private void ecoHandle(ObserverMessage arg) {
        ObserverMessage mes = arg;

        EconomicDataDTO dataDTO = JSON.toJavaObject(JSON.parseObject(mes.getData()), EconomicDataDTO.class);
        if (dataDTO == null || BusinessCycleEnum.findByKey(dataDTO.getCycle()) == null) {
            System.out.println("无法识别经济周期" + dataDTO);
            return;
        }

        BusinessCycleEnum businessCycle = BusinessCycleEnum.findByKey(dataDTO.getCycle());
        switch (businessCycle) {
            case EXPANSION:
                System.out.println("债券需求理论--经济扩张，债券需求/供给增加，需求/供给曲线右移；均衡利率上升/下降取决于哪个曲线移动幅度更大");
                break;
            case PEAK:
                System.out.println("债券需求理论--经济顶峰，债券需求/供给增加，需求/供给曲线右移；均衡利率上升/下降取决于哪个曲线移动幅度更大");
                break;
            case CONTRACTION:
                System.out.println("债券需求理论--经济紧缩，债券需求/供给减少，需求/供给曲线左移；均衡利率上升/下降取决于哪个曲线移动幅度更大");
                break;
            case TROUGH:
                System.out.println("债券需求理论--经济低谷，债券需求/供给减少，需求/供给曲线左移；均衡利率上升/下降取决于哪个曲线移动幅度更大");
                break;
            default:
                break;
        }
    }
}
