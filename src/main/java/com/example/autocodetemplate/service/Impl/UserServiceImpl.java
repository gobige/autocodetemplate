package com.example.autocodetemplate.service.Impl;

import com.example.autocodetemplate.domain.SysAccount;
import com.example.autocodetemplate.service.UserService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.extern.slf4j.Slf4j;
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
 * 实现自定义度量指标
 */
// micrometer
//特性 1多维度度量 支持Tag 2 预设大量探针 缓存，类加载器，GC，CPU利用率，线程池
@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService, MeterBinder {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private Counter loginCounter = null;

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        this.loginCounter = meterRegistry.counter("login.count");
    }

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

        loginCounter.increment();
        return true;
    }
}
