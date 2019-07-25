package com.example.autocodetemplate.thirdparty.param.req;

import com.alibaba.fastjson.JSONObject;
import com.example.autocodetemplate.exception.ServiceRuntimeException;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * <p></p>
 * <p>Project: </p>
 * <p>ModuleID: thirdParty---juhe</p>
 * <p>Comments: 开奖结果查询</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class LotteryQueryJuheRequest extends JuheRequest implements Serializable {
    private static final long serialVersionUID = -1493500034562650656L;

    /**
     * 请求url
     */
    private String REQUEST_API_URL = "/lottery/query";

    /**
     * 彩票id
     * @see com.example.autocodetemplate.enums.EnumLotteryQueryType
     */
    private String lottery_id;

    /**
     * 彩票期号
     */
    private String lottery_no;

    @Override
    public String buildUrl(String host) {
        setReqestUrl(host+REQUEST_API_URL);

        // 初始化请求地址
        StringBuilder stringBuilder = new StringBuilder(getReqestUrl());
        stringBuilder.append("?key=").append (this.getAppKey());
        stringBuilder.append("&lottery_id=").append (this.getLottery_id());
        stringBuilder.append("&lottery_no=").append (this.getLottery_no());

        return stringBuilder.toString();
    }

    @Override
    public JSONObject buildJson() {
        return null;
    }

    @Override
    public void checkParam()  throws ServiceRuntimeException{
        if (StringUtils.isEmpty(this.getAppKey())) {
            throw new ServiceRuntimeException("三方接口--请求参数key不能为null-->" + this.getAppKey());
        }
        if (StringUtils.isEmpty(this.getLottery_id())) {
            throw new ServiceRuntimeException("三方接口--请求参数lottery_id不能为null-->" + this.getLottery_id());
        }
        if (StringUtils.isEmpty(this.getLottery_no())) {
            throw new ServiceRuntimeException("三方接口--请求参数lottery_no不能为null-->" + this.getLottery_no());
        }
    }

    public String getREQUEST_API_URL() {
        return REQUEST_API_URL;
    }

    public void setREQUEST_API_URL(String REQUEST_API_URL) {
        this.REQUEST_API_URL = REQUEST_API_URL;
    }

    public String getLottery_id() {
        return lottery_id;
    }

    public void setLottery_id(String lottery_id) {
        this.lottery_id = lottery_id;
    }

    public String getLottery_no() {
        return lottery_no;
    }

    public void setLottery_no(String lottery_no) {
        this.lottery_no = lottery_no;
    }

}
