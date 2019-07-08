package com.project.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * @author 费 宇
 * @version v1.0
 * @ClassName: SmsCode
 * @Description: 发送验证码类
 * @date 2019年06月02日 21:14
 */
public class SmsCode {
    /**
     * 1、URL加手机号的键
     */
    private static final String URL = "http://v.juhe.cn/sms/send?mobile=";
    /**
     * 2、短信模板ID的键与值(162722)，和验证码的键；
     */
    private static final String TPL_ID = "&tpl_id=164237&tpl_value=";
    /**
     * 3、短信应用接口的键与值
     */
    private static final String KEY = "&key=bba6b55b2842970742a62a99b5f75a84";
    /**
     * 4、字符集格式
     */
    private static final String ENCODING = "UTF-8";
    /**
     * 5、短信回调数据验证
     */
    private static final String ERROR_CODE = "\"error_code\":0";
    /**
     * 6、验证码的键
     */
    private static final String CODE_KEY = "#code#=";

    /**
     * 调用接口发送短信
     *
     * @param phoneNumber 接收验证码的手机号
     * @param code        随机生成的验证码
     * @return 成功或者失败
     * @throws Exception 抛出异常
     */
    public static boolean sendCode(String phoneNumber, String code)
            throws Exception {
        /*1、将验证码封装为键值对方式*/
        String strCode = URLEncoder.encode(CODE_KEY + code, ENCODING);
        /*2、包装好URL对象，将接口地址包装在此对象中*/
        URL url = new URL(URL + phoneNumber + TPL_ID + strCode + KEY);
        /*3、打开连接，得到连接对象*/
        URLConnection connection = url.openConnection();
        /*4、向服务器发送连接请求*/
        connection.connect();
        /*5、获得服务器响应的数据*/
        BufferedReader bufferedReader = new BufferedReader
                (new InputStreamReader(connection.getInputStream(), ENCODING));
        /*6、建立StringBuffer对象，用于接收发送短信回调函数*/
        StringBuffer buffer = new StringBuffer();
        String lineDate = null;
        /*7、通过IO流，接收回调函数中的值*/
        while ((lineDate = bufferedReader.readLine()) != null) {
            buffer.append(lineDate);
        }
        /*8、关闭流*/
        bufferedReader.close();
        String strBuffer = buffer.toString();
        int verifyCode = strBuffer.indexOf(ERROR_CODE);
        boolean isOK = false;
        /*9、判断返回数据是否为真*/
        if (verifyCode >= 0) {
            isOK = true;
        }
        return isOK;
    }
}
