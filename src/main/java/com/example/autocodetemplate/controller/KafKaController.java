package com.example.autocodetemplate.controller;

import com.example.autocodetemplate.exception.ServiceRuntimeException;
import com.example.autocodetemplate.filter.KafkaProducerMesTestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/yates/template")
public class KafKaController {
    private final Logger logger = LoggerFactory.getLogger(KafKaController.class);

    @Resource
    private KafkaTemplate<String, String> template;

    @RequestMapping(value = "send.json", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> generateTemp(@RequestBody() KafkaProducerMesTestFilter testFilter) throws ServiceRuntimeException {
        Map<String, Object> result = new HashMap<>(1);


        return result;
    }
}
