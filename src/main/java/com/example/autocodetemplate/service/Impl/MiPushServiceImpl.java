package com.example.autocodetemplate.service.Impl;

import com.example.autocodetemplate.exception.ServiceRuntimeException;
import com.example.autocodetemplate.po.MiPushPO;
import com.example.autocodetemplate.service.MiPushService;
//import com.xiaomi.xmpush.server.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MiPushServiceImpl implements MiPushService {

    private final Logger logger = LoggerFactory.getLogger(MiPushServiceImpl.class);

    private static final String APP_KEY = "5551746657369";

    /**
     * 小米APP_SECRET
     */
    private static final String APP_SECRET = "rZwj5yeyjQAYd7XTlr9eig==";
    /**
     * 主包名
     */
    private static final String PACKAGE_NAME = "cn.carhouse.yctone";


    @Override
    public void push(MiPushPO miPushPO) throws Exception {
//        Constants.useOfficial();
//
//        Sender sender = new Sender(APP_SECRET);
//        String messagePayload = "This is a message";
//        String title = "notification title";
//        String description = "notification description";
//        Message message = new Message.Builder()
//                .title(title)
//                .description(description)
//                .payload(messagePayload)
//                .passThrough(0)
//                .restrictedPackageName(PACKAGE_NAME)
//                .notifyType(-1)     // 使用默认提示音提示
//                .build();
//
//
//        TargetedMessage targetedMessage = new TargetedMessage();
//        targetedMessage.setMessage(message);
//        targetedMessage.setTarget(TargetedMessage.TARGET_TYPE_REGID, "Js2cmFFTCZiqFLYTsBC1C/AnLvq+1TDeyWp/E00u/dMKozPv0Tmmdip7JmYwW2Oy");
//
////        Result result = sender.send(Arrays.asList(targetedMessage), 3);
//        Result result =  sender.send(message, Arrays.asList("Js2cmFFTCZiqFLYTsBC1C/AnLvq+1TDeyWp/E00u/dMKozPv0Tmmdip7JmYwW2Oy",
//                "Js2cmFFTCZiqFLYTsBC1C/AnLvq+1TDeyWp/E00u/dMKozPv0Tmmdip7JmYwW2Oy"), 3); //发送消息到一组设备上, regids个数不得超过1000个
//        logger.info("Server response: ", "MessageId: " + result.getMessageId()
//                + " ErrorCode: " + result.getErrorCode().toString()
//                + " Reason: " + result.getReason());

    }
}
