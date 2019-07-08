package com.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.RentHistoryBean;
import com.project.service.IRentHistoryService;
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

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: RentHistoryController
 * @Description: 出租交易历史记录 页面控制器
 * @date 2019年06月04日 14:20
 */
@Api("出租交易历史记录 页面控制器")
@RestController
@RequestMapping("/rentHistory")
public class RentHistoryController {

    @Autowired
    private IRentHistoryService iRentHistoryService;

    private Logger logger = LoggerFactory.getLogger(ContractController.class);

    @ApiOperation("查询出租用户的交易记录的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personalId", value = "出租用户ID", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "当前显示的条数", dataType = "String")
    })
    @RequestMapping("showRentOutHistoryPageAllByRentOutPersonalId")
    public ResponseData showRentOutHistoryPageAllByRentOutPersonalId(HttpSession session, String page, String size) {
        Integer personalId = (Integer) session.getAttribute("id");
        logger.info("收到showRentOutHistoryPageAllByRentOutPersonalId的请求:" + "personalId:"+ personalId + "page:" + page + "size:" + size);
        Page<RentHistoryBean> pages = iRentHistoryService.showRentOutHistoryPageAllByRentOutPersonalId(personalId,Integer.parseInt(page),Integer.parseInt(size));
        ResponseData responseData = new ResponseData();
        responseData.getData().put("pages", pages);
        logger.info("发送showRentOutHistoryPageAllByRentOutPersonalId的响应:" + responseData);
        return responseData;
    }

    @ApiOperation("查询租赁用户的交易记录的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personalId", value = "租赁用户ID", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "当前显示的条数", dataType = "String")
    })
    @RequestMapping("showLeaseHistoryPageAllByLeasePersonalId")
    public ResponseData showLeaseHistoryPageAllByLeasePersonalId(HttpSession session,  String page, String size) {
        Integer personalId = (Integer) session.getAttribute("id");
        logger.info("收到showLeaseHistoryPageAllByLeasePersonalId的请求:" + "personalId:"+ personalId + "page:" + page + "size:" + size);
        Page<RentHistoryBean> pages = iRentHistoryService.showLeaseHistoryPageAllByLeasePersonalId(personalId,Integer.parseInt(page),Integer.parseInt(size));
        ResponseData responseData = new ResponseData();
        responseData.getData().put("pages", pages);
        logger.info("发送showLeaseHistoryPageAllByLeasePersonalId的响应:" + responseData);
        return responseData;
    }
}
