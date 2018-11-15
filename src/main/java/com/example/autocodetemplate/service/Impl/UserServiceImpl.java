package com.example.autocodetemplate.service.Impl;

import com.example.autocodetemplate.domain.SysAccount;
import com.example.autocodetemplate.service.UserService;
import org.apache.commons.lang.SystemUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public boolean login(SysAccount sysAccount) {

        // 省略持久层
        if ("yates".equals(sysAccount.getLogingName()) &&  "123456".equals(sysAccount.getPassWord())) {
        }else if ("root".equals(sysAccount.getLogingName()) &&  "123456".equals(sysAccount.getPassWord())) {
        }else if ("test".equals(sysAccount.getLogingName()) &&  "123456".equals(sysAccount.getPassWord())) {
        }else {
            return false;
        }

        UsernamePasswordToken token = new UsernamePasswordToken(sysAccount.getLogingName(), sysAccount.getPassWord());
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            logger.info("There is no user with username of " + token.getPrincipal());
        } catch (IncorrectCredentialsException ice) {
            logger.info("Password for account " + token.getPrincipal() + " was incorrect!");
        } catch (LockedAccountException lae) {
            logger.info("The account for username " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");
        }

        return true;
    }
}
