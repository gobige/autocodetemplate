package com.example.autocodetemplate.util;

import com.example.autocodetemplate.service.Impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class MD5 {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public static String md5(String plainText) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(plainText.getBytes("UTF-8"));
            byte s[] = m.digest();
            StringBuilder result = new StringBuilder();
            for(byte i : s){
                result.append(Integer.toHexString((0x000000FF&i)|0xFFFFFF00).substring(6));
            }
            return result.toString();
        } catch (Exception e) {
            logger.error("md5加密出错,{}",e.getMessage());
        }
        return null;
    }
}
