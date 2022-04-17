package com.example.autocodetemplate.finance.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaleGoods {
    private BigDecimal saleVolum;
    private Goods goods;
    private BigDecimal fixedCost;

    /**
     * 获取总收入
     * 平均收入即[价格]
     * @return
     */
    public BigDecimal getTotalRevenue() {
        return this.goods.getPrice().multiply(saleVolum);
    }

    public BigDecimal getTotalCost() {
        return goods.getVariableCost().multiply(saleVolum).add(fixedCost);
    }

    /**
     * MC,AVC,ATC都是U slope
     * @return
     */
    public BigDecimal getAvgCost() {
        return getTotalCost().divide(saleVolum);
    }

    /**
     * 长期是否生产临界值
     *
     */
    public BigDecimal getBreakevenPoint() {
        return fixedCost.divide(goods.getPrice().subtract(goods.getVariableCost()));
    }

    public boolean longTermProduct() {
        if (this.saleVolum.compareTo(getBreakevenPoint()) == 1) {
            return true;
        }

        return false;
    }

    /**
     * 短期是否生产
     * @return
     */
    public boolean shortTermProduct() {
        if (goods.getPrice().compareTo(goods.getVariableCost()) == 1) {
            return true;
        }

        return false;
    }
}
