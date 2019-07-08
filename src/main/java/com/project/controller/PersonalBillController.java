package com.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.PersonalBillBean;
import com.project.service.IPersonalBillService;
import com.project.util.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: PersonalBillController
 * @Description: 个人账单 页面控制器
 * @date 2019年06月04日 14:13
 */
@Api("个人账单 页面控制器")
@RestController
@RequestMapping("/personalBill")
public class PersonalBillController {

    @Autowired
    private IPersonalBillService iPersonalBillService;

    private Logger logger = LoggerFactory.getLogger(com.project.controller.CbdBillController.class);

    @ApiOperation("动态查询 个人账单请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "当前显示的条数", dataType = "String")
    })
    @RequestMapping("showPersonalPageByCondition")
    public ResponseData showPersonalPageByCondition(HttpSession session, String startTime, String endTime, @RequestParam(value = "page", defaultValue = "1") String page, @RequestParam(defaultValue = "10") String size) {
        logger.info("收到showPersonalPageByCondition的请求:" + "startTime:" + startTime + "endTime:" + endTime +
                "page:" + page + "size:" + size);
        Map<String, String> map = new HashMap<>(10);
        Integer id = (Integer) session.getAttribute("id");
        map.put("personalId", id + "");
        if (startTime != null && !"".equals(startTime)) {
            map.put("startTime", startTime);
        }
        if (endTime != null && !"".equals(endTime)) {
            map.put("endTime", endTime);
        }
        map.put("page", page);
        map.put("size", size);
        Page<PersonalBillBean> personalBillPage = iPersonalBillService.showPersonalPageByCondition(map);
        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.getData().put("personalBill", personalBillPage);
        logger.info("发送showPersonalPageByCondition的响应:" + responseData);
        return responseData;
    }

    @ApiOperation("统计请求方法")
    @ApiImplicitParam(name = "CountEntity", value = "统计实体类", dataType = "CountEntity")
    @RequestMapping("PersonalCount")
    public ResponseData personalCount(HttpSession session) {
        logger.info("收到PersonalCount请求");
        ResponseData responseData = new ResponseData();
        responseData.getData().put("count", iPersonalBillService.personalCount((Integer) session.getAttribute("id"))); //(Integer) session.getAttribute("id")
        logger.info("发送PersonalCount的响应:" + responseData);
        return responseData;
    }
}

