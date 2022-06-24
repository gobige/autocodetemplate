package com.example.autocodetemplate.finance.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuotaDTO {
    /**
     * 名字
     */
    private String name;
    /**
     * 代码
     */
    private String code;
    /**
     * 当前价格
     */
    private String curPrice;
    /**
     * 昨收
     */
    private String yesClosingPrice;
    /**
     * 今开
     */
    private String nowOpenPrice;
    /**
     * 成交量（手）
     */
    private String volume;
    /**
     * 外盘
     */
    private String buyVol;
    /**
     * 内盘
     */
    private String sellVol;
    private String 买一;
    private String 买一量;
    private String 买二;
    private String 卖一;
    private String 卖一量;
    private String 卖二卖五;
    private String 最近逐笔成交;
    /**
     * 时间
     */
    private String time;
    /**
     * 涨跌
     */
    private String upAndDown;
    /**
     * 涨跌%
     */
    private String upAndDownPer;
    /**
     * 最高
     */
    private String highest;
    /**
     * 最低
     */
    private String lowest;
    /**
     * 价格/成交量（手）/成交额
     */
    private String 价格成交量成交额;
    /**
     * 价成交量（手）
     */
    private String 成交量手;
    /**
     * 成交额（万）
     */
    private String 成交额;
    /**
     * 换手率
     */
    private String turnoverRate;
    /**
     * 市盈率
     */
    private String pe;
    private String 最高;
    private String 最低;
    private String 振幅;
    /**
     * 流通市值
     */
    private String circulatingMarketVal;
    /**
     * 总市值
     */
    private String totalMarketVal;
    /**
     * 市净率
     */
    private String pb;
    private String 涨停价;
    private String 跌停价;
}
