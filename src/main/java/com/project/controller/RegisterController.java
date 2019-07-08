package com.project.controller;

import com.project.bean.PersonalBean;
import com.project.service.IPersonalService;
import com.project.util.ResponseData;
import com.project.util.SmsCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author 费 宇
 * @version v1.0
 * ClassName:LoginController
 * Description: 登录验证类
 * @date 2019年06月02日 21:07
 */
@Api("注册页面控制器")
@RestController
public class RegisterController {
    @Autowired
    private IPersonalService personalService;
    /**
     * 获取日志记录对象
     */
    private Logger logger = LoggerFactory.getLogger(RegisterController.class);

    /**
     * 判断用户名是否存在的页面控制器
     *
     * @param username 用户名
     * @return 是否重复
     */
    @ApiOperation("个人用户注册判断用户名是否存在的方法")
    @ApiImplicitParam(name = "username", value = "存储用户名", dataType = "String")
    @RequestMapping("registerIsUsername")
    public String isUsernameByUsername(String username) {
        logger.info("收到registerIsUsername(判断用户名是否存在)的请求，用户名为：" + username);
        String valid = personalService.findPersonalByUsername(username) == 0 ?
                "{\"valid\":true}" : "{\"valid\":false}";
        logger.info("响应registerIsUsername(判断用户名是否存在)的请求，状态为：" + valid);
        return valid;
    }


    @ApiOperation("验证码发送方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNumber", value = "手机号码", dataType = "String"),
            @ApiImplicitParam(name = "session", value = "存储数据的session", dataType = "HttpSession")
    })
    @RequestMapping("registerCode")
    public ResponseData verifyCode(String phoneNumber, HttpSession session) {
        logger.info("收到registerCode(发送手机验证码)的请求，手机号为：" + phoneNumber);
        ResponseData responseData = null;
        try {
            if ("".equals(phoneNumber.trim()) || phoneNumber.length() == 0) {
                responseData = new ResponseData(400, "手机号码为空！");
                return responseData;
            }
            //手机号码格式判断
            String regex = "^1[3|4|5|7|8|9]\\d{9}$";
            if (!Pattern.matches(regex, phoneNumber)) {
                responseData = new ResponseData(400, "手机格式错误！");
                return responseData;
            }
            //生成一个6位0~9之间的随机字符串
            /*验证码长度*/
            int codeLength = 6;
            StringBuffer buffer = new StringBuffer();
            Random random = new Random();
            for (int i = 0; i < codeLength; i++) {
                buffer.append(random.nextInt(10));
            }
            if (!SmsCode.sendCode(phoneNumber, buffer.toString())) {
                responseData = new ResponseData(400, "验证码发送失败！");
                return responseData;
            } else {
                //将验证码、手机号码和当前的系统时间存储到session中
                session.setAttribute("code", buffer.toString());
                session.setAttribute("number", phoneNumber);
                session.setAttribute("time", System.currentTimeMillis());
                responseData = new ResponseData(200, "验证码发送成功！");
                return responseData;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.unauthorized();
        } finally {
            logger.info("响应registerCode(发送手机验证码)的请求，响应信息为：" + responseData);
        }
    }

    @ApiOperation("个人用户注册方法")
    @ApiImplicitParam(name = "personalBean", value = "存储用户信息的bean对象", dataType = "PersonalBean")
    @RequestMapping("registerPersonal")
    public ResponseData registerUser(PersonalBean personalBean, HttpSession session) {
        logger.info("收到registerPersonal(个人用户注册)的请求，注册信息(bean对象)为：" + personalBean);
        ResponseData responseData = null;
        /*1、获取session中的验证码，原有验证码*/
        String oldCode = (String) session.getAttribute("code");
        /*2、获取session中原有的电话号码。*/
        String oldPhoneNumber = (String) session.getAttribute("number");
        /*3、获取session中的发送验证码的时间*/
        long oldTime = (long) session.getAttribute("time");
        /*4、问题信息拼接*/
        StringBuffer buffer = new StringBuffer();
        /*5、判断条件是否成立*/
        boolean codeIsOk = false;
        boolean phoneIsOk = false;
        boolean timeIsOk = false;
        /*6、判断验证码前后是否一致*/
        if (personalBean.getSalt().equals(oldCode)) {
            codeIsOk = true;
        } else {
            buffer.append("验证码不一致！");
        }
        /*7、判断手机号码是否一致*/
        if (personalBean.getTel().equals(oldPhoneNumber)) {
            phoneIsOk = true;
        } else {
            buffer.append("手机号码不一致！");
        }
        /*8、判断是否超过有效时期，十分钟*/
        long tenMinute = 1000 * 60 * 10;
        if ((System.currentTimeMillis() - oldTime) < tenMinute) {
            timeIsOk = true;
        } else {
            buffer.append("超过十分钟，验证码已失效！");
        }
        /*9、判断验证码、手机号、时间有效期，满足时执行注册方法*/
        if (codeIsOk && phoneIsOk && timeIsOk) {
            /*三目运算符，注册返回值为0时，返回注册失败。非0则返回ok*/
            responseData = (personalService.registerUserAndPersonal(personalBean) == 0) ?
                    new ResponseData(400, "用户注册失败！") : ResponseData.ok();
        } else {
            String message = buffer.toString();
            responseData = new ResponseData(400, message);
        }
        logger.info("响应registerPersonal(个人用户注册)的请求，响应信息为：" + responseData);
        return responseData;
    }
}
