package com.example.autocodetemplate.finance.enums;

import com.example.autocodetemplate.finance.domain.Goods;
import com.example.autocodetemplate.finance.domain.SaleGoods;
import com.example.autocodetemplate.ohter.practice.Apple;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum MarketCompTypeEnum {
    PERFECT_COMPETITION(1, "完全竞争市场", "产品无差异化，壁垒低，没有定价权，长期只有normal profit"),
    MONOPOLISTIC_COMPETITION(2, "垄断竞争市场", "产品差异化，brand认知，advertising，需求曲线向下，any price power"),
    OLIGOPOLY(3, "寡头市场", "less firm，difficult enter，产品无/少差异化，price power；kinked demand--nash equilibrium---cournot--dominant firm"),
    PURE_MONOPOLY(4, "垄断市场", "legal/natural barrier，单厂商，产品单一，some price of power，受政府干预");

    private Integer key;
    private String value;
    private String desc;

    MarketCompTypeEnum(Integer key, String value, String description) {
        this.key = key;
        this.value = value;
        this.desc = description;
    }

    public static MarketCompTypeEnum findByKey(Integer key) {
        for (MarketCompTypeEnum typeEnum : MarketCompTypeEnum.values()) {
            if (typeEnum.getKey().equals(key)) {
                return typeEnum;
            }
        }

        return null;
    }

    /**
     * 获取市场集中度 N-Firm
     *
     * @return
     */
    public BigDecimal getNfirmConcentrateRatio(List<SaleGoods> saleGoods, int n) {
        if (CollectionUtils.isEmpty(saleGoods)) {
            return BigDecimal.ZERO;
        }

        long sum = saleGoods.stream().collect(Collectors.summingLong(g -> g.getSaleVolum().longValue()));
        List<SaleGoods> sorts = saleGoods.stream().sorted(Comparator.comparing(SaleGoods::getSaleVolum).reversed()).collect(Collectors.toList());

        BigDecimal nTop = BigDecimal.ZERO;
        for (int i = 0; i < n; i++) {
            nTop = nTop.add(sorts.get(i).getSaleVolum());
        }

        return nTop.divide(BigDecimal.valueOf(sum));
    }

    /**
     * 获取市场集中度  herfindahl-hirschman index
     *
     * @return
     */
    public BigDecimal getHHI(List<SaleGoods> saleGoods) {
        if (CollectionUtils.isEmpty(saleGoods)) {
            return BigDecimal.ZERO;
        }

        long sum = saleGoods.stream().collect(Collectors.summingLong(g -> g.getSaleVolum().longValue()));

        BigDecimal total = BigDecimal.ZERO;
        BigDecimal divisor = BigDecimal.valueOf(sum);

        for (SaleGoods saleGood : saleGoods) {
            BigDecimal divide = saleGood.getSaleVolum().divide(divisor);
            total = total.add(divide.multiply(divide));
        }

        return total;
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
