package com.example.autocodetemplate.param.req;

import com.alibaba.fastjson.JSONObject;
import com.example.autocodetemplate.exception.ServiceRuntimeException;

import java.io.Serializable;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public abstract class JuheRequest implements Serializable {
    private static final long serialVersionUID = -4695058778507090119L;
    /**
     * appkey
     */
    private String appKey;
    /**
     *
     */
    private String reqestUrl;

    /**
     *
     * @param host
     * @return
     */
    public abstract String buildUrl(String host);

    /**
     *
     * @return
     */
    public abstract JSONObject buildJson();

    /**
     *
     */
    public abstract void checkParam() throws ServiceRuntimeException;
    public String getReqestUrl() {
        return reqestUrl;
    }

    public void setReqestUrl(String reqestUrl) {
        this.reqestUrl = reqestUrl;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
