package com.example.autocodetemplate.controller.alibaba;

import java.math.BigDecimal;

public class Alibaba2 {
    public static void main(String[] args) throws Exception {
        GoodsPriceCalculateDTO priceCalculateDTO = new GoodsPriceCalculateDTO();
        BigDecimal generalPrice = new BigDecimal("88.88");
        priceCalculateDTO.setGeneralPrice(generalPrice);

        User user = new User();
        user.setCompanyType(3);
        user.setUserLevel(1);
        priceCalculateDTO.setUser(user);

        PriceService priceService = new PriceServiceImpl();
        BigDecimal resultPrice = priceService.calculPrice(priceCalculateDTO);
        System.out.println(resultPrice);
    }
}

class PriceServiceImpl implements PriceService {
    @Override
    public BigDecimal calculPrice(GoodsPriceCalculateDTO goodsPriceCalculateDTO) throws Exception {
        if (goodsPriceCalculateDTO == null || goodsPriceCalculateDTO.getUser() == null) {
            throw new Exception("会员商品折扣价计算，传入参数错误！");
        }
        User user = goodsPriceCalculateDTO.getUser();

        Integer level = user.getUserLevel();
        Integer companyType = user.getCompanyType();
        BigDecimal generalPrice = goodsPriceCalculateDTO.getGeneralPrice();

        CompanyEnum companyEnum = CompanyEnum.findByKey(companyType);
        if (companyEnum == null) {
            return null;
        }
        Company company = new Company(companyEnum);

        Calculate calculate = company.getCalCulateInstance();
        if (calculate == null) {
            return null;
        }

        BigDecimal resultPrice =  calculate.calculate(generalPrice, level);
        return resultPrice;
    }
}

interface Calculate {

    BigDecimal calculate(BigDecimal generalPrice, Integer level);
}

class ACompanyCalculate implements Calculate {
    public ACompanyCalculate(){}

    @Override
    public BigDecimal calculate(BigDecimal generalPrice, Integer level) {

        AUserDiscountEnum aUserDiscountEnum = AUserDiscountEnum.findByKey(level);
        if (aUserDiscountEnum == null || generalPrice == null) {
            return null;
        }

        BigDecimal resultPrice = new BigDecimal(aUserDiscountEnum.getDiscount()).multiply(generalPrice);

        return resultPrice.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }
}
class BCompanyCalculate implements Calculate {
    public BCompanyCalculate(){}

    @Override
    public BigDecimal calculate(BigDecimal generalPrice, Integer level) {

        BUserDiscountEnum bUserDiscountEnum = BUserDiscountEnum.findByKey(level);
        if (bUserDiscountEnum == null || generalPrice == null) {
            return null;
        }

        BigDecimal resultPrice = new BigDecimal(bUserDiscountEnum.getDiscount()).multiply(generalPrice);

        return resultPrice.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }
}
class CCompanyCalculate implements Calculate {
    public CCompanyCalculate(){}

    @Override
    public BigDecimal calculate(BigDecimal generalPrice, Integer level) {

        CUserDiscountEnum cUserDiscountEnum = CUserDiscountEnum.findByKey(level);
        if (cUserDiscountEnum == null || generalPrice == null) {
            return null;
        }

        BigDecimal resultPrice = new BigDecimal(cUserDiscountEnum.getDiscount()).multiply(generalPrice);

        return resultPrice.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }
}

class Company {
    public Company(){}

    public Company(CompanyEnum companyEnum) {
        this.companyEnum = companyEnum;
    }

    private CompanyEnum companyEnum;

    public Calculate getCalCulateInstance() throws IllegalAccessException, InstantiationException {

        Calculate calculate = companyEnum.getCalculate();

        return calculate;
    }

}

enum CompanyEnum {

    A_COMPANY(1, "a公司", "a公司站点", new ACompanyCalculate()),
    B_COMPANY(2, "b公司", "b公司站点", new BCompanyCalculate()),
    C_COMPANY(3, "c公司", "c公司站点", new CCompanyCalculate());

    CompanyEnum(Integer key, String value, String description, Calculate castClass) {
        setKey(key);
        setValue(value);
        setDescription(description);
        setCalculate(castClass);
    }

    private Integer key;
    private String value;
    private String description;
    private Calculate calculate;

    public static CompanyEnum findByKey(Integer key) {
        for (CompanyEnum companyEnum : CompanyEnum.values()) {
            if (companyEnum.getKey().equals(key)) {
                return companyEnum;
            }
        }

        return null;
    }

    public Calculate getCalculate() {
        return calculate;
    }

    public void setCalculate(Calculate calculate) {
        this.calculate = calculate;
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


    public static AUserDiscountEnum findByKey(Integer key) {
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

    public static BUserDiscountEnum findByKey(Integer key) {
        for (BUserDiscountEnum bUserDiscountEnum : BUserDiscountEnum.values()) {
            if (bUserDiscountEnum.getKey().equals(key)) {
                return bUserDiscountEnum;
            }
        }

        return null;
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

enum CUserDiscountEnum {
    GOLP(1, "皇冠会员", "8折优惠", new Double("0.8").doubleValue()),
    SILVER_MEDAL(2, "普通用户", "7.5折优惠", new Double("0.75").doubleValue());

    CUserDiscountEnum(Integer key, String value, String description, Double discount) {
        setKey(key);
        setDiscount(discount);
        setValue(value);
        setDescription(description);
    }

    private Integer key;
    private Double discount;
    private String value;
    private String description;


    public static CUserDiscountEnum findByKey(Integer key) {
        for (CUserDiscountEnum cUserDiscountEnum : CUserDiscountEnum.values()) {
            if (cUserDiscountEnum.getKey().equals(key)) {
                return cUserDiscountEnum;
            }
        }

        return null;
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

interface PriceService {

    /**
     * 获取商品最终折扣价格
     * @param goodsPriceCalculateDTO dto
     * @return
     */
    BigDecimal calculPrice(GoodsPriceCalculateDTO goodsPriceCalculateDTO) throws Exception;

}

class GoodsPriceCalculateDTO {
    private BigDecimal generalPrice;
    private User user;

    public BigDecimal getGeneralPrice() {
        return generalPrice;
    }

    public void setGeneralPrice(BigDecimal generalPrice) {
        this.generalPrice = generalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

class User {
    private Integer companyType;

    private Integer userLevel;

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }
}
