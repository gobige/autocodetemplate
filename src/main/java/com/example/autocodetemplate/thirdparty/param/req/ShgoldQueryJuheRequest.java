package com.example.autocodetemplate.thirdparty.param.req;

import com.alibaba.fastjson.JSONObject;
import com.example.autocodetemplate.exception.ServiceRuntimeException;
import org.springframework.util.StringUtils;

import java.io.Serializable;

public class ShgoldQueryJuheRequest extends JuheRequest implements Serializable {
    private static final long serialVersionUID = -1493500034562650656L;

    /**
     * 请求url
     */
    private String REQUEST_API_URL = "/finance/gold/shgold";

    private String v = "1";


    @Override
    public String buildUrl(String host) {
        setReqestUrl(host+REQUEST_API_URL);

        // 初始化请求地址
        StringBuilder stringBuilder = new StringBuilder(getReqestUrl());
        stringBuilder.append("?key=").append ("b5197338b69a1d640ce49efda43d57a2");
        stringBuilder.append("?v=").append (v);

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
    }

    public String getREQUEST_API_URL() {
        return REQUEST_API_URL;
    }

    public void setREQUEST_API_URL(String REQUEST_API_URL) {
        this.REQUEST_API_URL = REQUEST_API_URL;
    }
}
