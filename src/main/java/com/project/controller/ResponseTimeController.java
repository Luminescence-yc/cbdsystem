package com.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.ResponseTimeBean;
import com.project.entity.ResponseTimeEntity;
import com.project.service.IResponseTimeService;
import com.project.util.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 罗亚辉
 * @ClassName:响应时间控制器
 * @Description:
 * @date 2019年05月31日 10:41
 */
@Api("响应时间控制器")
@RestController
@RequestMapping("/responseTime")
public class ResponseTimeController {
    @Autowired
    IResponseTimeService responseTimeService;

    /**
     * 统计所有方法最近几次执行平均耗时
     * @param num 次数
     * @return 响应对象
     */
    @ApiOperation("统计所有方法最近几次执行平均耗时")
    @ApiImplicitParam(name = "num",value = "次数",dataType = "int")
    @RequestMapping("findAVGResponseTimeByNum")
    public ResponseData findAVGResponseTimeByNum(@RequestParam(value="num", defaultValue="3")int num){
        ResponseData responseData=new ResponseData();
        List<ResponseTimeBean> list= responseTimeService.findAVGResponseTimeByNum(num);
        responseData.getData().put("list",list);
        return responseData;
    }

    /**
     * 根据方法名查询该方法的执行耗时
     * @param function  方法名
     * @param currentPage   当前页
     * @param pageSize  显示条数
     * @return  响应对象
     */
    @ApiOperation("根据方法名查询该方法的执行耗时")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "function",value = "方法名",dataType = "String"),
            @ApiImplicitParam(name = "currentPage",value = "当前页",dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "显示条数",dataType = "int"),
    })

    @RequestMapping("findResponseTimeByFunctionName")
    public ResponseData findResponseTimeByFunctionName(
            String function,
            @RequestParam(value="currentPage", defaultValue="1")int currentPage,
            @RequestParam(value="pageSize", defaultValue="20")int pageSize){
        ResponseData responseData=new ResponseData();
        Page<ResponseTimeEntity> page=new Page<>(currentPage,pageSize);
        IPage<ResponseTimeBean> iPage =responseTimeService.findResponseTimeByFunctionName(function,page);
        responseData.getData().put("page",iPage);
        return responseData;
    }
}
