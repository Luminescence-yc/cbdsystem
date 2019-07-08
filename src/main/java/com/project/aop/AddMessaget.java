//package com.project.aop;
//
//import com.project.annotation.MessageType;
//import com.project.bean.PersonalBean;
//import com.project.bean.UserBean;
//import com.project.entity.MessageEntity;
//import com.project.service.IMessageService;
//import com.project.service.IPersonalService;
//import com.project.service.IRentMessageService;
//import com.project.service.ISellMessageService;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.lang.annotation.Annotation;
//import java.sql.Date;
//
//*
// * @author 杨彪
// * @ClassName:添加推送消息
// * @Description:
// * @date 2019年06月05日 11:26
//
//
//@Repository
//@Aspect
//public class AddMessaget   {
//    @Autowired
//    private IMessageService messageService;//消息
//    @Autowired
//    private IPersonalService personalService;//个人用户
//    @Autowired
//    private IRentMessageService rentMessageService;//出租留言、出售留言、出租预定、出售预定
//    @After("execution(* com.project.service.impl.SellMessageServiceImpl.add*(..))||execution(* com.project.service.impl.RentMessageServiceImpl.add*(..))")//*表示返回值类型+后面的包名+类名+方法名+参数
//    public void afterAdvice(JoinPoint joinPoint)throws Throwable{
//        HttpServletRequest request=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
//        HttpSession session=request.getSession();
//        MessageType messageType=joinPoint.getTarget().getClass().getAnnotation(MessageType.class);
//        MessageEntity messageEntity=new MessageEntity();
//        Object user=session.getAttribute("id");//个人用户对象
//        PersonalBean personalBean= (PersonalBean) user;
//        System.out.println(personalBean);
//        if (messageType!=null){
//            messageEntity.setMessagetitle(messageType.addMessage());//消息标题
//        }
//        messageEntity.setPersonalid(personalBean.getId());//个人用户Id
//    personalBean.getUsername();//用户1
//
//        //需要用户1和用户2  拼接成消息内容 添加给下方
//        messageEntity.setContent("用户1"+"对"+"用户2"+"发送了预定请求");//消息内容（待修改）
//
//        messageEntity.setMessagetime(new Date(System.currentTimeMillis()));//消息时间
//        messageEntity.setMessagetype("个人消息");//消息类型
//        messageService.addMessage(messageEntity);
//
//    }
//}
