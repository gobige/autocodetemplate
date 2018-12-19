package com.example.autocodetemplate.controller.shiro;

import com.example.autocodetemplate.domain.SysAccount;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.Collection;

public class MyRealm extends AuthorizingRealm {


    public String getName() {
        return "MyRealm1";
    }

    protected AuthorizationInfo doGetAuthorizationInfo(
     PrincipalCollection principals) {
     String username = (String) principals.fromRealm(getName()).iterator().next();

     if( username != null ){
        // 查询用户授权信息
         Collection<String> pers = new ArrayList<>();
          if( pers != null && !pers.isEmpty() ){
             SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
             info.addStringPermissions(pers);
             return info;
          }
     }

     return null;
 }

 // 获取认证信息
 protected AuthenticationInfo doGetAuthenticationInfo(
     AuthenticationToken authcToken ) throws AuthenticationException {
     UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
     // 通过表单接收的用户名
     String userName = token.getUsername();
     String password = token.getPassword().toString();
     if (!"yates".equals(userName)) {
         throw new UnknownAccountException(); // 如果用户名错误
     }
     if (!"123".equals(password)) {
         throw new IncorrectCredentialsException(); // 如果密码错误
     }
//     SysAccount account = businessManager.get( username );
     SysAccount account = new SysAccount();
     account.setLogingName(userName);
     account.setPassWord(password);
     if (account != null) {
         return new SimpleAuthenticationInfo(account.getLogingName(), account.getPassWord(), getName());
     }
     return null;
 }


}
