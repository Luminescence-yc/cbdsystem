package com.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.SellHistoryBean;
import com.project.service.ISellHistoryService;
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
 * @ClassName: SellHistoryController
 * @Description: 出售交易历史记录 页面控制器
 * @date 2019年06月04日 14:21
 */
@Api("出售交易历史记录 页面控制器")
@RestController
@RequestMapping("/sellHistory")
public class SellHistoryController {

    @Autowired
    private ISellHistoryService iSellHistoryService;

    private Logger logger = LoggerFactory.getLogger(com.project.controller.CbdBillController.class);


    @ApiOperation("查询出售用户的交易记录的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personalId", value = "出租用户ID", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "当前显示的条数", dataType = "String")
    })
    @RequestMapping("showSellHistoryPageAllByPersonalId")
    public ResponseData showSellHistoryPageAllByPersonalId(HttpSession session, String page, String size) {
        Integer personalId = (Integer) session.getAttribute("id");
        logger.info("收到showSellHistoryPageAllByPersonalId的请求:" + "personalId:"+ personalId + "page:" + page + "size:" + size);
        Page<SellHistoryBean> pages = iSellHistoryService.showSellHistoryPageAllByPersonalId(2,Integer.parseInt(page),Integer.parseInt(size));
        ResponseData responseData = new ResponseData();
        responseData.getData().put("pages", pages);
        logger.info("发送showSellHistoryPageAllByPersonalId的响应:" + responseData);
        return responseData;
    }

}
