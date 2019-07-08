package com.project.aop;

import com.project.annotation.ClassType;
import com.project.bean.RoleBean;
import com.project.bean.UserBean;
import com.project.entity.LogEntity;
import com.project.entity.UserEntity;
import com.project.service.ILogService;
import org.apache.shiro.web.session.HttpServletSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 罗亚辉
 * @ClassName:添加操作日志
 * @Description:
 * @date 2019年06月02日 20:40
 */
@Repository
@Aspect
public class AddLog {
    @Autowired
    private ILogService logService;
    @After("execution(* com.project.controller.LoginController.*(..))")
    public void addLoginLog(JoinPoint jp) throws Throwable{
        //获取当前线程request对象
        HttpServletRequest request=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
        //获取当前线程session
        HttpSession session=request.getSession();
        //获取当前登陆对象
        Object user=session.getAttribute("user");
        //获取当前执行的方法名
        String function=jp.getSignature().getName();
        //获取方法的参数
        Object[] args = jp.getArgs();
        StringBuilder arg=new StringBuilder();
        for (Object o : args) {
            if (o!=null)
                arg.append(o.toString()+';');
        }
        //获取类的自定义注解
        ClassType classType=jp.getTarget().getClass().getAnnotation(ClassType.class);
        String describe="";
        if (classType!=null)
            describe=classType.describe();
        if (user!=null){
            UserBean userBean= (UserBean) user;
            RoleBean roleBean=userBean.getRoleBeans().get(0);
            LogEntity logEntity=new LogEntity();
            logEntity.setIp(getIpAddress(request));
            logEntity.setUsername(userBean.getUsername());
            String userType=roleBean.getRoleName().endsWith("管理员")?"管理员":roleBean.getRoleName();
            if (roleBean.getRoleName().endsWith("管理员")){
                logEntity.setLogBelong("后台");
            }
            else{
                logEntity.setLogBelong("前台");
            }
            if (function.startsWith("login")){
                logEntity.setLogType("登录");
                logEntity.setOperationRecord(userType+"登陆");
            }else if (function.startsWith("logout")){
                logEntity.setLogType("注销");
                logEntity.setOperationRecord(userType+"注销");
            }
            if (function.startsWith("add")){
                logEntity.setLogType("操作");
                logEntity.setOperationRecord("添加了"+describe+",参数:"+arg);
            }else if (function.startsWith("update")){
                logEntity.setLogType("操作");
                logEntity.setOperationRecord("修改了"+describe+",参数:"+arg);
            }else if (function.startsWith("delete")){
                logEntity.setLogType("操作");
                logEntity.setOperationRecord("删除了"+describe+",参数:"+arg);
            }
            logService.addLog(logEntity);
        }
    }
    @After("execution(* com.project.dao.impl.*.add*(..))")
    public void addMethod(JoinPoint pj) throws Throwable{
        LogEntity logEntity=new LogEntity();
        logEntity.setLogBelong("操作");

    }
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
