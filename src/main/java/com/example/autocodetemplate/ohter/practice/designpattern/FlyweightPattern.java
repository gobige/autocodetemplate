package com.example.autocodetemplate.ohter.practice.designpattern;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class FlyweightPattern {
    private static final ExpressCompanyEnum express[] = ExpressCompanyEnum.values();

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            DeliverFare deliverFare = CacheFactory.getDeliverFare(getRandomExpressCompany());
            deliverFare.setUnitNum(getDeliverUnitNum());
            deliverFare.caculate();
        }
    }

    private static ExpressCompanyEnum getRandomExpressCompany() {
        return express[(int)(Math.random()*express.length)];
    }

    private static int getDeliverUnitNum() {
        return (int)(Math.random() * 10);
    }

}

class CacheFactory {
    private static Map<String,DeliverFare> deliverFareMap = new HashMap<>();

    public static DeliverFare getDeliverFare(ExpressCompanyEnum companyEnum) {
        if (deliverFareMap.containsKey(companyEnum.getKey())) {
            System.out.println("获取以前对象");
            return deliverFareMap.get(companyEnum.getKey());
        }else {
            DeliverFare deliverFare = new DeliverFare(companyEnum);
            deliverFareMap.put(companyEnum.getKey(),deliverFare);
            System.out.println("创建新对象");
            return deliverFare;
        }
    }
}

class DeliverFare implements ExpressDelivery {
    private Integer unitNum;
    private ExpressCompanyEnum company;
    DeliverFare(ExpressCompanyEnum company) {
        this.company = company;
    }

    @Override
    public void caculate() {
        System.out.println(company.getValue() + "计算运费，货物数量：" + unitNum);
    }

    public Integer getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(Integer unitNum) {
        this.unitNum = unitNum;
    }

    public ExpressCompanyEnum getCompany() {
        return company;
    }

    public void setCompany(ExpressCompanyEnum company) {
        this.company = company;
    }
}

enum ExpressCompanyEnum {
    SHUNFENG("shunfeng","顺丰"),
    ZHONGTONG("zhongtong","中通"),
        JD("jd","京东");

    ExpressCompanyEnum(String key,String value) {
        this.key = key;
        this.value = value;
    }
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

interface ExpressDelivery {
    void caculate();
}