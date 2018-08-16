package com.example.autocodetemplate.service;

import com.example.autocodetemplate.exception.ServiceRuntimeException;
import com.example.autocodetemplate.param.req.LotteryQueryJuheRequest;
import com.example.autocodetemplate.param.resp.LotteryQueryJuheResponse;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public interface LotteryQueryService {

    /**
     * 开奖结果查询
     */
    LotteryQueryJuheResponse getLotteryResults(LotteryQueryJuheRequest request) throws ServiceRuntimeException;
}
