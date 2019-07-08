package com.project.configuration.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author 万晓川
 * @ClassName:自定义Token
 * @Description:
 * @date 2019年06月6日 9:10
 */
public class UserNamePassWordCookieToken extends UsernamePasswordToken {

    /**
     * 是否记住密码
     */
    private boolean isRemember;
    /**
     * 登录用户类型，1为个人用户，2为企业用户，3到7为管理员用户
     */
    private String loginType;

    public UserNamePassWordCookieToken(String username, String password, boolean isRemember, String loginType) {
        super(username, password, isRemember);
        this.setRemember(isRemember);
        this.loginType = loginType;
    }

    public boolean isRemember() {
        return isRemember;
    }

    public void setRemember(boolean remember) {
        isRemember = remember;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

}
