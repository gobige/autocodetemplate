package com.example.autocodetemplate.finance.domain;

import com.example.autocodetemplate.finance.enums.BusinessCycleEnum;
import com.example.autocodetemplate.finance.enums.InventoryToSaleEnum;
import com.example.autocodetemplate.finance.enums.LableLevelEnum;

import java.math.BigDecimal;

public class EconomicIndex {
    private LableLevelEnum lableLevelEnum;
    private InventoryToSaleEnum inventoryToSaleEnum;
    private BusinessCycleEnum businessCycleEnum;

    /**
     * 通货膨胀率
     */
    private BigDecimal inflation;
    /**
     * cpi
     */
    private BigDecimal cpi;
    /**
     * ppi
     */
    private BigDecimal ppi;
    /**
     * 失业率
     */
    private BigDecimal unemployment;
    /**
     * 平均工作时长
     */
    private BigDecimal averageWeeklyHoursManufacturing;
    /**
     * 平均失业保险申领人数
     */
    private BigDecimal averageWeeklyInitialClaimsForUnemploymentInsurance;
    /**
     * 消费品，制造业新订单指数
     */
    private BigDecimal ManufacNewOrders;
    /**
     * 房屋新建指数
     */
    private BigDecimal BuildingPermitsForNew;
    /**
     * 密歇根消费者预期指数
     */
    private BigDecimal consumerExpectUniverOfMichigan ;
    /**
     * 标普500
     */
    private BigDecimal SP500;
    /**
     * 货币供给M2
     */
    private BigDecimal moneySupplyRealM2 ;
    /**
     * 10年国债利率 - 隔夜借款利率 利差
     */
    private BigDecimal Intrate10YearTreasuryAndOvernightBorrowRates;

    /**
     * 财政政策度  1 扩展；0 中性；-1 紧缩
     */
    private Integer fiscalPolicy;
    /**
     * 财政政策度  1 扩展；0 中性；-1 紧缩
     */
    private Integer currencyPolicy;
    /**
     * r财政乘数
     */
    private BigDecimal FiscalMultiplier;
    /**
     * 货币乘数
     */
    private BigDecimal monetaryMultiplier ;
    /**
     * 存款准备金率
     */
    private BigDecimal reserveRequirement ;
}
