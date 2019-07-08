package com.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CompanyBillBean;
import com.project.service.ICompanyBillService;
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

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: CompanyBillController
 * @Description: 企业账单 页面控制器
 * @date 2019年06月04日 13:17
 */
@Api("企业账单 页面控制器")
@RestController
@RequestMapping("/companyBill")
public class CompanyBillController {

    @Autowired
    private ICompanyBillService iCompanyBillService;

    private Logger logger = LoggerFactory.getLogger(CompanyBillController.class);

    @ApiOperation("动态条件查询企业账单的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "申请开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "申请结束时间", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "当前显示的条数", dataType = "String")
    })
    @RequestMapping("showCompanyBillPageByCondition")
    public ResponseData showCompanyBillPageByCondition(HttpSession session,String startTime, String endTime, String page, String size){
        logger.info("收到findAllByCondition的请求:" + "startTime:" + startTime + "endTime:" + endTime +
                 "page:" + page + "size:" + size);
        System.out.println(page+"   "+size);

        Map<String, String> map = new HashMap<>(10);

        Integer id = (Integer)session.getAttribute("id");
        map.put("companyId",id + "");

        if (startTime != null && !"".equals(startTime)) {
            map.put("startTime", startTime);
        }
        if (endTime != null && !"".equals(endTime)) {
            map.put("endTime", endTime);
        }
        map.put("page", page);
        map.put("size", size);
       Page<CompanyBillBean> pages = iCompanyBillService.showCompanyBillPageByCondition(map);
        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.getData().put("pages", pages);
        logger.info("发送showCompanyBillPageByCondition的响应:" + responseData);
        return responseData;

    }
    @ApiOperation("统计请求方法")
    @ApiImplicitParam(name = "CountEntity", value = "统计实体类", dataType = "CountEntity")
    @RequestMapping("CompanyCount")
    public ResponseData companyCount(HttpSession session) {
        logger.info("收到CompanyCount请求");
        ResponseData responseData = new ResponseData();
        responseData.getData().put("count", iCompanyBillService.companyCount((Integer) session.getAttribute("id"))); //(Integer) session.getAttribute("id")
        logger.info("发送CompanyCount的响应:" + responseData);
        return responseData;
    }

}
