package com.example.autocodetemplate.finance.domain;

import com.example.autocodetemplate.finance.enums.ElasticGoodsTypeEnum;
import com.example.autocodetemplate.finance.enums.GoodsMarketTypeEnum;
import com.example.autocodetemplate.finance.enums.MarketCompTypeEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Goods {
    private String name;

    private BigDecimal price;

    private BigDecimal variableCost;

    private ElasticGoodsTypeEnum elasType;

    private MarketCompTypeEnum compType;

    private GoodsMarketTypeEnum marketType;

    /**
     * 受代替品，时间，收入占比影响
     * <1：缺乏弹性；
     * >1：富有弹性
     */
    private Double priceElastics;
    /**
     * <0：互补品；
     * >0：代替品
     */
    private Double crossElastics;
    /**
     * <0：inferior；
     * >0：normal
     * <1：necessary；
     * >1：luxury
     */
    private Double incomeElastics;

    /**
     * 需求预测
     * @param pSelf 自身价格
     * @param income 平均收入
     * @param pOhter 互补/替代品 商品价格
     * @return
     */
    public BigDecimal demandForecast(CalItem pSelf, CalItem income, CalItem pOhter) {
        return BigDecimal.valueOf(pSelf.calItem())
                .add(BigDecimal.valueOf(income.calItem()))
                .add(BigDecimal.valueOf(pOhter.calItem()));
    }

    public BigDecimal supplyForecast(CalItem pSelf, CalItem salary, CalItem cost) {
        return BigDecimal.valueOf(pSelf.calItem())
                .add(BigDecimal.valueOf(salary.calItem()))
                .add(BigDecimal.valueOf(cost.calItem()));
    }
}
