package com.example.autocodetemplate.util;

import com.example.autocodetemplate.domain.SysAccount;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class AccountUtils {

    private static final Logger logger = LoggerFactory.getLogger(AccountUtils.class);

    /*
     * 获取用户信息
     *
     */
    public static SysAccount getOssAccount() {
        Map<String, String> userMap = getCurrentUserInfo();

        if (userMap.isEmpty()) {
            return null;
        }

        String idStr = userMap.get("id");
        String userIdStr = userMap.get("user_id");
        String userTypeStr = userMap.get("user_type");

        SysAccount account = new SysAccount();
        try {
            account.setId(Integer.parseInt(idStr));
            account.setUserId(Integer.parseInt(userIdStr));
            account.setUserType(Integer.parseInt(userTypeStr));
        } catch (Exception e) {
            logger.error("获取passport用户数据，解析出错---id【{}】，userId【{}】，userType【{}】",idStr,userIdStr,userTypeStr);
        }

        return account;
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    public static Map<String, String> getCurrentUserInfo() {
        Subject subject = SecurityUtils.getSubject();
        SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) subject.getPrincipals();
        List<Object> listPrincipals = principalCollection.asList();
        Map<String, String> attributes = (Map<String, String>) listPrincipals.get(1);
        return attributes;
    }
}
