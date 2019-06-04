package com.example.autocodetemplate.service;

import com.example.autocodetemplate.po.MiPushPO;

public interface MiPushService {

    /**
     *
     * @param miPushPO
     */
    void push(MiPushPO miPushPO) throws Exception;
}
