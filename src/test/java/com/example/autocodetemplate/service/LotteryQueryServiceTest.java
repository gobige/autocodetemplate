package com.example.autocodetemplate.service;

import com.example.autocodetemplate.Enum.EnumLotteryQueryType;
import com.example.autocodetemplate.exception.ServiceRuntimeException;
import com.example.autocodetemplate.param.req.LotteryQueryJuheRequest;
import com.example.autocodetemplate.param.resp.LotteryQueryJuheResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LotteryQueryServiceTest {
    @Resource
    private LotteryQueryService lotteryQueryService;


    @Test
    public void testGetssqResult()  throws ServiceRuntimeException {
        LotteryQueryJuheRequest request = new LotteryQueryJuheRequest();
        request.setLottery_id(EnumLotteryQueryType.SSQ.getKey());
        request.setLottery_no("18085");

        LotteryQueryJuheResponse response = lotteryQueryService.getLotteryResults(request);
        if (response != null) {
            if (response.getResult() != null) {
                System.out.println(response.getResult());
            }
        }
    }

}
