package com.example.autocodetemplate.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.autocodetemplate.exception.ServiceRuntimeException;
import com.example.autocodetemplate.param.req.JuheRequest;
import com.example.autocodetemplate.param.req.LotteryQueryJuheRequest;
import com.example.autocodetemplate.param.resp.JuheResponse;
import com.example.autocodetemplate.param.resp.LotteryQueryJuheResponse;
import com.example.autocodetemplate.service.LotteryQueryService;
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
@Service("lotteryQueryService")
public class LotteryQueryServiceImpl implements LotteryQueryService {

    private final Logger logger = LoggerFactory.getLogger(LotteryQueryServiceImpl.class);

    @Value(" ${com.yates.thirdparty.juhe.key}")
    private String key;
    @Value("${com.yates.thirdparty.juhe.host}")
    private String host;
    @Override
    public LotteryQueryJuheResponse getLotteryResults(LotteryQueryJuheRequest request) {
        LotteryQueryJuheResponse response = new LotteryQueryJuheResponse();
        JSONObject jsonObject = handleGetRequest(request);

        String reason = JSONObject.parseObject(jsonObject.toJSONString()).getString("reason");
        Integer error_code = JSONObject.parseObject(jsonObject.toJSONString()).getInteger("error_code");
        response.setReason(reason);
        response.setError_code(error_code);

        // result
        LotteryQueryJuheResponse.LotteryQueryJuheResult result = response.new LotteryQueryJuheResult();
        JSONObject resultObj = JSONObject.parseObject(jsonObject.toJSONString()).getJSONObject("result");

        result.setLottery_id(JSONObject.parseObject(resultObj.toJSONString()).getString("lottery_id"));
        result.setLottery_date(JSONObject.parseObject(resultObj.toJSONString()).getString("lottery_date"));
        result.setLottery_exdate(JSONObject.parseObject(resultObj.toJSONString()).getString("lottery_exdate"));
        result.setLottery_name(JSONObject.parseObject(resultObj.toJSONString()).getString("lottery_name"));
        result.setLottery_no(JSONObject.parseObject(resultObj.toJSONString()).getString("lottery_no"));
        result.setLottery_pool_amount(JSONObject.parseObject(resultObj.toJSONString()).getString("lottery_pool_amount"));
        result.setLottery_res(JSONObject.parseObject(resultObj.toJSONString()).getString("lottery_res"));
        result.setLottery_sale_amount(JSONObject.parseObject(resultObj.toJSONString()).getString("lottery_sale_amount"));

        JSONArray lotteryPrice = JSONObject.parseObject(resultObj.toJSONString()).getJSONArray("lottery_prize");

        List<LotteryQueryJuheResponse.LotteryQueryJuheResult.LotteryPrize> lotteryPrizes = new ArrayList<>();
        for (int i = 0; i < lotteryPrice.size(); i++) {
            LotteryQueryJuheResponse.LotteryQueryJuheResult.LotteryPrize prize = result.new LotteryPrize();
            prize.setPrize_amount(lotteryPrice.getJSONObject(0).getString("prize_amount"));
            prize.setPrize_name(lotteryPrice.getJSONObject(0).getString("prize_name"));
            prize.setPrize_num(lotteryPrice.getJSONObject(0).getString("prize_num"));
            prize.setPrize_require(lotteryPrice.getJSONObject(0).getString("prize_require"));
            lotteryPrizes.add(prize);
        }

        result.setLottery_prize(lotteryPrizes);


        response.setResult(result);

        return response;
    }


    /**
     * 纯jsonobject转换response对象
     * @param jsonObject
     * @param clazz
     * @param <T>
     * @return
     */
    private <T extends JuheResponse> T pureJsonObjectParse(JSONObject jsonObject,Class<T> clazz) {
        T result = JSONObject.parseObject(jsonObject.toJSONString(),clazz);
        if (result == null) {
            throw new ServiceRuntimeException("聚合数据--jsonobject parse error");
        }

        return result;
    }

    /**
     * 发起请求
     * @param request
     * @return
     */
    private JSONObject handleGetRequest(JuheRequest request) {
        if (request == null)
            throw new ServiceRuntimeException("请求参数不能为null！");

        request.setAppKey(key);
        request.checkParam();
        String url = request.buildUrl(host);

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
