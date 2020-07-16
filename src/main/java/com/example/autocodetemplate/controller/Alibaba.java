package com.example.autocodetemplate.controller;

import java.math.BigDecimal;

public class Alibaba {
    public static void main(String[] args) {
        PriceService priceService = new PriceServiceImpl();
        priceService.getPriceByGoodsId(new Integer(1), new User());
    }
}

interface PriceService {

    /**
     * 通过商品id获取不同商品价格
     *
     * @param goodsId     商品id
     * @return user 用户
     */
    BigDecimal getPriceByGoodsId(Integer goodsId, User user);

}

class PriceServiceImpl implements PriceService {
    @Override
    public BigDecimal getPriceByGoodsId(Integer goodsId, User user) {

        CompanyEnum companyEnum = user.getCompanyEnum();
        Integer userLevel = user.getUserLevel();

        // query general price through db
        BigDecimal generalPrice = new BigDecimal("88.88");

        BigDecimal bigDecimal = generalPrice;
        if (companyEnum == companyEnum.A_COMPANY) {
            bigDecimal = AUserDiscountEnum.calculPriceByLevel(generalPrice, userLevel);

        } else if (companyEnum == companyEnum.B_COMPANY) {
            bigDecimal = BUserDiscountEnum.calculPriceByLevel(generalPrice, userLevel);

        } else if (companyEnum == companyEnum.C_COMPANY) {
            bigDecimal = CUserEnum.calculPriceByLevel(generalPrice, userLevel);
        }

        return bigDecimal;
    }
}

class User {
    private CompanyEnum companyEnum;

    private Integer userLevel;

    public CompanyEnum getCompanyEnum() {
        return companyEnum;
    }

    public void setCompanyEnum(CompanyEnum companyEnum) {
        this.companyEnum = companyEnum;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }
}

enum CompanyEnum {

    A_COMPANY(1, "a公司", "a公司站点"),
    B_COMPANY(2, "b公司", "b公司站点"),
    C_COMPANY(3, "c公司", "c公司站点");

    CompanyEnum(Integer key, String value, String description) {
        setKey(key);
        setValue(value);
        setDescription(description);
    }


    private Integer key;
    private String value;
    private String description;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

enum AUserDiscountEnum {

    SUPER_VIP(1, "超级VIP用户", "7折优惠", new Double("0.7").doubleValue()),
    VIP(2, "VIP用户", "9折优惠", new Double("0.9").doubleValue()),
    GENERAL(3, "普通用户", "无优惠", new Double("1").doubleValue());

    AUserDiscountEnum(Integer key, String value, String description, Double discount) {
        setKey(key);
        setDiscount(discount);
        setValue(value);
        setDescription(description);
    }

    public static BigDecimal calculPriceByLevel(BigDecimal decimal, Integer userLevel) {

        AUserDiscountEnum userEnum = findByKey(userLevel);
        if (userEnum == null) {
            return decimal;
        }

        BigDecimal resultPrice = new BigDecimal(userEnum.discount).multiply(decimal);

        return resultPrice.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

    private static AUserDiscountEnum findByKey(Integer key) {
        for (AUserDiscountEnum aUserEnum : AUserDiscountEnum.values()) {
            if (aUserEnum.getKey().equals(key)) {
                return aUserEnum;
            }
        }

        return null;
    }

    private Integer key;
    private Double discount;
    private String value;
    private String description;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}

enum BUserDiscountEnum {
    GOLP(1, "金牌用户", "6.5折优惠", new Double("0.65").doubleValue()),
    SILVER_MEDAL(2, "银牌客户", "7.5折优惠", new Double("0.75").doubleValue()),
    BRONZE_MEDAL(3, "铜牌客户", "8.5折优惠", new Double("0.85").doubleValue()),
    GENERAL(3, "普通用户", "无优惠", new Double("1").doubleValue());

    BUserDiscountEnum(Integer key, String value, String description, Double discount) {
        setKey(key);
        setDiscount(discount);
        setValue(value);
        setDescription(description);
    }

    private Integer key;
    private Double discount;
    private String value;
    private String description;

    private static BUserDiscountEnum findByKey(Integer key) {
        for (BUserDiscountEnum bUserDiscountEnum : BUserDiscountEnum.values()) {
            if (bUserDiscountEnum.getKey().equals(key)) {
                return bUserDiscountEnum;
            }
        }

        return null;
    }

    public static BigDecimal calculPriceByLevel(BigDecimal decimal, Integer userLevel) {
        BUserDiscountEnum bUserDiscountEnum = findByKey(userLevel);
        if (bUserDiscountEnum == null) {
            return decimal;
        }

        BigDecimal resultPrice = new BigDecimal(bUserDiscountEnum.discount).multiply(decimal);

        return resultPrice.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

enum CUserEnum {
    GOLP(1, "皇冠会员", "8折优惠", new Double("0.8").doubleValue()),
    SILVER_MEDAL(2, "普通用户", "7.5折优惠", new Double("0.75").doubleValue());

    CUserEnum(Integer key, String value, String description, Double discount) {
        setKey(key);
        setDiscount(discount);
        setValue(value);
        setDescription(description);
    }

    private Integer key;
    private Double discount;
    private String value;
    private String description;


    private static CUserEnum findByKey(Integer key) {
        for (CUserEnum cUserEnum : CUserEnum.values()) {
            if (cUserEnum.getKey().equals(key)) {
                return cUserEnum;
            }
        }

        return null;
    }

    public static BigDecimal calculPriceByLevel(BigDecimal decimal,Integer userLevel) {
        CUserEnum cUserEnum = findByKey(userLevel);
        if (cUserEnum == null) {
            return decimal;
        }

        BigDecimal resultPrice;
        if (cUserEnum == GOLP) {
            resultPrice = new BigDecimal("0.8").multiply(decimal);
        } else if (cUserEnum == SILVER_MEDAL) {
            resultPrice = new BigDecimal("0.75").multiply(decimal);
        } else {
            resultPrice = decimal;
        }

        return resultPrice.setScale(2,BigDecimal.ROUND_HALF_DOWN);
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}