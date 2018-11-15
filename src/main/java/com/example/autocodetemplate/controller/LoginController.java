package com.example.autocodetemplate.controller;

import com.example.autocodetemplate.domain.SysAccount;
import com.example.autocodetemplate.service.UserService;
import com.example.autocodetemplate.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/in.json")
    @ResponseBody
    public Map<String, Object> in(@RequestBody() SysAccount sysAccount) {
        Map<String, Object> result = new HashMap<>();
        boolean success = userService.login(sysAccount);

        if (success) {
            result.put("code", 0);
            result.put("bcode",0);
            result.put("mes", "登录成功");
        } else {
            result.put("code", 0);
            result.put("bcode",1);
            result.put("mes", "登录失败");
        }
        return result;
    }

    @RequestMapping("/out.json")
    @ResponseBody
    public Map<String, Object> out(@RequestBody() SysAccount sysAccount) {
        Map<String, Object> result = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        result.put("code", 0);
        result.put("bcode",0);
        result.put("mes", "登出成功");

        return result;
    }


}
