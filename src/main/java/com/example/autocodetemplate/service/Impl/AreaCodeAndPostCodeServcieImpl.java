package com.example.autocodetemplate.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.autocodetemplate.domain.AreaAndPostCodeResult;
import com.example.autocodetemplate.exception.ServiceRuntimeException;
import com.example.autocodetemplate.param.req.JuheRequest;
import com.example.autocodetemplate.param.req.LotteryQueryJuheRequest;
import com.example.autocodetemplate.param.resp.JuheResponse;
import com.example.autocodetemplate.param.resp.LotteryQueryJuheResponse;
import com.example.autocodetemplate.service.AreaCodeAndPostCodeServcie;
import com.example.autocodetemplate.util.HttpGetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */

@Service(value = "areaCodeAndPostCodeServcie")
public class AreaCodeAndPostCodeServcieImpl implements AreaCodeAndPostCodeServcie {
    private Logger logger = LoggerFactory.getLogger(GenerateTemplateServiceImpl.class);


    @Override
   public List<AreaAndPostCodeResult> getAreaCodeAndPostCode(String name) throws Exception{
        String url = "http://zhouxunwang.cn/data/?id=110&key=BL7OrNRkStn+iJyA+owwTmjIOATgsJeZ/pxz7P0&name=" +name;
        JSONObject jsonObject = HttpGetUtils.httpGet(url);
        if (jsonObject == null) {
            return new ArrayList<>();
        }
        JSONArray lotteryPrice = JSONObject.parseObject(jsonObject.toJSONString()).getJSONArray("result");

        List<AreaAndPostCodeResult> lotteryPrizes = new ArrayList<>();
        for (int i = 0; i < lotteryPrice.size(); i++) {
            AreaAndPostCodeResult prize = new AreaAndPostCodeResult();
            prize.setProvince(lotteryPrice.getJSONObject(i).getString("province"));
            prize.setCity(lotteryPrice.getJSONObject(i).getString("city"));
            prize.setArea(lotteryPrice.getJSONObject(i).getString("area"));
            prize.setPostCode(lotteryPrice.getJSONObject(i).getString("post_code"));
            prize.setAreaCode(lotteryPrice.getJSONObject(i).getString("area_code"));
            lotteryPrizes.add(prize);
        }

        return lotteryPrizes;
    }

}
