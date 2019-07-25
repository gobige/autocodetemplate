package com.example.autocodetemplate.thirdparty;

import com.example.autocodetemplate.exception.ServiceRuntimeException;
import com.example.autocodetemplate.thirdparty.param.req.LotteryQueryJuheRequest;
import com.example.autocodetemplate.thirdparty.param.req.ShgoldQueryJuheRequest;
import com.example.autocodetemplate.thirdparty.param.resp.ShgoldQueryJuheResponse;

public interface ShgoldQueryService {

    /**
     * 开奖结果查询
     */
    ShgoldQueryJuheResponse getGoldResults(ShgoldQueryJuheRequest request) throws ServiceRuntimeException;
}
