package com.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.LogRequestBean;
import com.project.entity.LogEntity;
import com.project.service.ILogService;
import com.project.util.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @author 罗亚辉
 * @ClassName:日志控制器
 * @Description:
 * @date 2019年05月30日 14:52
 */
@Api("日志控制器")
@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private ILogService logService;
    @ApiOperation("条件查询日志")
    @ApiImplicitParam(name = "logRequestBean",value = "查询条件bean",dataType = "LogRequestBean")
    @RequestMapping(value = "findLog",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseData findLogByPageAndCondition(LogRequestBean logRequestBean, HttpServletRequest request){
        if (logRequestBean.getStartTime()!=null){
            try {
                Date.valueOf(logRequestBean.getStartTime());
            }catch (Exception e){
                return new ResponseData(-1,"日期数据格式错误");
            }
        }
        if (logRequestBean.getEndTime()!=null){
            try {
                Date.valueOf(logRequestBean.getEndTime());
            }catch (Exception e){
                return new ResponseData(-1,"日期数据格式错误");
            }
        }
        IPage<LogEntity> page=logService.findLogByPageAndCondition(logRequestBean);
        ResponseData responseData=new ResponseData();
        responseData.getData().put("page",page);
        return responseData;
    }
}
