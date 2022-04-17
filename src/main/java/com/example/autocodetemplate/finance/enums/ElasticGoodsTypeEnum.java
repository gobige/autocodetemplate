package com.example.autocodetemplate.finance.enums;

import com.example.autocodetemplate.exception.ServiceRuntimeException;
import com.example.autocodetemplate.finance.constant.FinancialConstants;

import java.math.BigDecimal;

public enum ElasticGoodsTypeEnum {
    NORMAL(1, "normal", "正常商品"),
    NECESSARY(2, "necessary ", "必需品"),
    INFERIOR(3, "inferior", "劣质商品"),
    GIFFEN(4, "giffen", "吉芬商品"),
    LUXURY(5, "luxury ", "奢侈品"),
    VEBLEN(6, "veblen", "韦伯伦");

    private Integer key;
    private String value;
    private String desc;

    ElasticGoodsTypeEnum(Integer key, String value, String description) {
        this.key = key;
        this.value = value;
        this.desc = description;
    }

    /**
     * 获取价格弹性值
     * @param beforPrice
     * @param afterPrice
     * @param beforQuota
     * @param afterQuota
     * @return
     * @throws ServiceRuntimeException
     */
    public static double getpriceElastics(BigDecimal beforPrice, BigDecimal afterPrice, Long beforQuota, Long afterQuota) throws ServiceRuntimeException {
        if (beforPrice == null || afterPrice == null || beforQuota == null || afterQuota == null) {
            throw new ServiceRuntimeException("参数不能为空");
        }

        long changeQuota = afterQuota - beforQuota;
        BigDecimal deltaPrice =BigDecimal.valueOf(changeQuota).divide(afterPrice.subtract(beforPrice)) ;

        BigDecimal priceDivQuato = afterPrice.divide(BigDecimal.valueOf(afterQuota), FinancialConstants.PRICE_SCALE, BigDecimal.ROUND_HALF_DOWN);

        return deltaPrice.multiply(priceDivQuato).doubleValue();
    }


    public static ElasticGoodsTypeEnum findByKey(Integer key) {
        for (ElasticGoodsTypeEnum typeEnum : ElasticGoodsTypeEnum.values()) {
            if (typeEnum.getKey().equals(key)) {
                return typeEnum;
            }
        }

        return null;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
