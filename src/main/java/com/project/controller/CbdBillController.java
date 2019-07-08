package com.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CbdBillBean;
import com.project.service.ICbdBillService;
import com.project.util.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: CbdBillController
 * @Description: 平台账单 页面控制器
 * @date 2019年06月03日 15:07
 */
@Api("平台账单 页面控制器")
@RestController
@RequestMapping("/cbdBill")
public class CbdBillController {

    @Autowired
    private ICbdBillService iCbdBillService;

    private Logger logger = LoggerFactory.getLogger(CbdBillController.class);

    @ApiOperation("动态查询 平台账单请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "当前显示的条数", dataType = "String")
    })
    @RequestMapping("showAdminPageByCondition")
    public ResponseData showAdminPageByCondition(String startTime, String endTime, String page, String size) {

        logger.info("收到showAdminPageByCondition的请求:" + "startTime:" + startTime + "endTime:" + endTime +
                "page:" + page + "size:" + size);

        if (startTime.length() == 0) {
            startTime=null;
        }
        if (endTime.length() == 0) {
            endTime=null;
        }
        Map<String, String> map = new HashMap<>(10);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("page", page);
        map.put("size", size);
        Page<CbdBillBean> page1 = iCbdBillService.showAdminPageByCondition(map);
        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.getData().put("pages", page1);
        logger.info("发送showAdminPageByCondition的响应:" + responseData);
        return responseData;
    }

    @ApiOperation("统计请求方法")
    @ApiImplicitParam(name = "CountEntity", value = "统计实体类", dataType = "CountEntity")
    @RequestMapping("adminCount")
    public ResponseData adminCount() {
        logger.info("收到adminCount请求");
        ResponseData responseData = new ResponseData();
        responseData.getData().put("count", iCbdBillService.adminCount());
        logger.info("发送adminCount的响应:" + responseData);
        return responseData;
    }
}
