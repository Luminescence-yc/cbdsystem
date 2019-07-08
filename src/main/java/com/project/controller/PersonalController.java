package com.project.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.collect.Maps;
import com.project.bean.PersonalBean;
import com.project.bean.UserBean;
import com.project.service.IPersonalService;
import com.project.util.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author 朱 骞
 * @version v1.0
 * @ClassName: PersonalController
 * @Description: 个人用户页面控制器
 * @date 2019年05月31日 16:28
 */
@Api("个人用户信息页面控制器")
@RestController
@RequestMapping("/personalInfo")
public class PersonalController {
    @Autowired
    private IPersonalService personalService;

    private Logger logger= LoggerFactory.getLogger(PersonalController.class);
    @ApiOperation("查询个人用户信息的请求方法")
    @ApiImplicitParam(name = "id",value = "个人用户id",dataType = "int")
    @RequestMapping("getPersonalById")
    public ResponseData getPersonalById(HttpSession session){
        Integer personalId= (Integer) session.getAttribute("id");
        logger.info("收到getPersonalById的请求:"+"id:");
        PersonalBean personalBean=personalService.getPersonalById(personalId);
        if(personalBean!=null){
            ResponseData responseData=new ResponseData();
            responseData.getData().put("personal",personalBean);
            logger.info("发送getPersonalById的响应:"+responseData);
            return responseData;
        }
        logger.info("发送getPersonalById的响应:"+ResponseData.unauthorized());
        return ResponseData.unauthorized();
    }
    @ApiOperation("修改个人用户信息的请求方法")
    @ApiImplicitParam(name = "personalBean",value = "个人用户实体Bean",dataType = "personalBean")
    @RequestMapping("updatePersonalInfoById")
    public ResponseData updatePersonalInfoById(HttpSession session,PersonalBean personalBean){
        Integer personalId= (Integer) session.getAttribute("id");
        UserBean userBean = (UserBean) session.getAttribute("user");
        int userId= userBean.getId();
        personalBean.setId(personalId);
        personalBean.setUserId(userId);
        logger.info("收到updatePersonalInfoById请求：" + personalBean.toString());
         int i=personalService.updatePersonalInfoById(personalBean);
         if(i==0){
             logger.info("发送updatePersonalInfoById响应：" + ResponseData.notFound());
             return ResponseData.notFound();
         }
        logger.info("发送updatePersonalInfoById响应：" + ResponseData.ok());
         return ResponseData.ok();
    }

}

