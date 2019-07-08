package com.project.configuration.shiro;

import com.project.bean.PowerBean;
import com.project.bean.RoleBean;
import com.project.bean.UserBean;
import com.project.entity.PowerEntity;
import com.project.entity.RoleEntity;
import com.project.entity.UserEntity;
import com.project.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 万晓川
 * @version v1.0
 * @ClassName MyShiroRealm
 * @Description Shiro进行身份认证和权限认证
 * @date 2019年05月30日 14:28
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserBean userBean = (UserBean) principals.getPrimaryPrincipal();
        for (RoleBean role : userBean.getRoleBeans()) {
            authorizationInfo.addRole(role.getRole());
            for (PowerBean power : role.getPowerBeans()) {
                authorizationInfo.addStringPermission(power.getPower());
            }
        }
        return authorizationInfo;
    }


    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     *
     * @param token 令牌
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserBean userBean = userService.findByUsername(username);
        System.out.println("----->>userInfo=" + userBean);
        if (userBean == null) {
            return null;
        }
        HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = httpRequest.getSession();
        session.setAttribute("user", userBean);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userBean,
                userBean.getPassword(),
                ByteSource.Util.bytes(userBean.getSalt()),
                getName()
        );
        return authenticationInfo;
    }

}