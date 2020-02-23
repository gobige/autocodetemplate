package com.example.autocodetemplate.support;

import com.example.autocodetemplate.domain.SysAccount;
import com.example.autocodetemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 自定义健康检查
 */
@Component
public class HealthTestIndicator implements HealthIndicator {
    @Autowired
    private UserService userService;

    @Override
    public Health health() {
        boolean success = userService.login(new SysAccount());
        Health health;
        if (success) {
            health = Health.up()
                    .withDetail("loginStatus", success)
                    .withDetail("message", "login success!.")
                    .build();
        } else {
            health = Health.down()
                    .withDetail("loginStatus", success)
                    .withDetail("message", "login failure!.")
                    .build();
        }
        return health;
    }
}
