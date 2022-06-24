package com.example.autocodetemplate.finance.api;

import com.example.autocodetemplate.finance.dto.QuotaDTO;
import com.example.autocodetemplate.finance.logic.StockLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("stock")
public class StockController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StockLogic stockLogic;


    @GetMapping("quote")
    @ResponseBody
    public List<QuotaDTO> quote(HttpSession httpSession, @RequestParam("code") String code) {

        List<QuotaDTO> quotaDTOS = stockLogic.getReleaseQuato(Arrays.asList(code));

        return quotaDTOS;
    }


}
