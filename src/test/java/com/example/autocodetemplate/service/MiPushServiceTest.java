package com.example.autocodetemplate.service;


import com.example.autocodetemplate.Enum.EnumLotteryQueryType;
import com.example.autocodetemplate.exception.ServiceRuntimeException;
import com.example.autocodetemplate.param.req.LotteryQueryJuheRequest;
import com.example.autocodetemplate.param.resp.LotteryQueryJuheResponse;
import com.example.autocodetemplate.po.MiPushPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MiPushServiceTest {
    @Resource
    private MiPushService miPushService;


    @Test
    public void testGetssqResult()  throws Exception {

        miPushService.push(new MiPushPO());

    }

}
