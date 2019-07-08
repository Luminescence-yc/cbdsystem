package com.project.controller;

import com.project.entity.OnlineUserEntity;
import com.project.service.IOnlineUserService;
import com.project.util.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 罗亚辉
 * @ClassName:在线人数控制器
 * @Description:
 * @date 2019年05月31日 14:12
 */
@Api("在线人数控制器")
@RestController
@RequestMapping("/onlineUser")
public class OnlineUserController {
    @Autowired
    private IOnlineUserService onlineUserService;

    /**
     * 查询每天在线人数最大值
     * @return 响应对象
     */
    @ApiOperation("查询每天在线人数最大值")
    @RequestMapping(value = "findAllDayMaxOnlineUser",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseData findAllDayMaxOnlineUser(){
        List<OnlineUserEntity> list= onlineUserService.findAllDayMaxOnlineUser();
        ResponseData responseData=new ResponseData();
        responseData.getData().put("list",list);
        return responseData;
    }

    /**
     * 根据日期查询当天在线人数
     * @param day 日期
     * @return
     */
    @ApiOperation("根据日期查询当天在线人数")
    @ApiImplicitParam(name = "day",value = "日期",dataType = "String")
    @RequestMapping("findOnlineUserByDay")
    public ResponseData findOnlineUserByDay(
            @RequestParam(value="day", defaultValue="")String day){
        if (day==null||day.length()==0)
            day=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        ResponseData responseData=new ResponseData();
        List<OnlineUserEntity> list=onlineUserService.findOnlineUserByDay(day);
        responseData.getData().put("list",list);
        return responseData;
    }
}
