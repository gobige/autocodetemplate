package com.example.autocodetemplate.thirdparty.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.autocodetemplate.exception.ServiceRuntimeException;
import com.example.autocodetemplate.thirdparty.ShgoldQueryService;
import com.example.autocodetemplate.thirdparty.param.req.JuheRequest;
import com.example.autocodetemplate.thirdparty.param.req.LotteryQueryJuheRequest;
import com.example.autocodetemplate.thirdparty.param.req.ShgoldQueryJuheRequest;
import com.example.autocodetemplate.thirdparty.param.resp.JuheResponse;
import com.example.autocodetemplate.thirdparty.param.resp.LotteryQueryJuheResponse;
import com.example.autocodetemplate.thirdparty.LotteryQueryService;
import com.example.autocodetemplate.thirdparty.param.resp.ShgoldQueryJuheResponse;
import com.example.autocodetemplate.util.HttpGetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("shgoldQueryService")
public class ShgoldQueryServiceImpl implements ShgoldQueryService {

    private final Logger logger = LoggerFactory.getLogger(ShgoldQueryServiceImpl.class);

    @Value("${com.yates.thirdparty.juhe.key}")
    private String key;
    @Value("${com.yates.thirdparty.juhe.host}")
    private String host;

    @Override
    public ShgoldQueryJuheResponse getGoldResults(ShgoldQueryJuheRequest request)  throws ServiceRuntimeException{
        ShgoldQueryJuheResponse response = new ShgoldQueryJuheResponse();
        JSONObject jsonObject = handleGetRequest(request);


        return response;
    }

    /**
     * 发起请求
     * @param request
     * @return
     */
    private JSONObject handleGetRequest(JuheRequest request) throws ServiceRuntimeException{
        if (request == null)
            throw new ServiceRuntimeException("请求参数不能为null！");

        request.setAppKey(key);
        request.checkParam();
        String url = request.buildUrl("http://web.juhe.cn:8080");

        logger.info("request_url_juhe:{}",url);
        JSONObject response = HttpGetUtils.httpGet(url);
        if(response == null) {
            throw new ServiceRuntimeException("接口请求异常！");
        }

//        T result = JSONObject.parseObject(response.toJSONString(),clazz);

//        if (result == null){
//            throw new ServiceRuntimeException("返回数据异常！");
//        }

        return response;
    }
}
