package com.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.bean.PersonalBean;
import com.project.bean.SellMessageBean;
import com.project.entity.SellMessageEntity;
import com.project.service.ISellMessageService;
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
 * @author 陈云龙
 * @version v1.0
 * @ClassName:SellParkingController
 * @Description:卖车预定留言控制器
 * @date 2019年06月03日 14:25
 */
@Api("卖车预定留言页面控制器")
@RestController
@RequestMapping("sellMessage")
public class SellMessageController {
    @Autowired
    private ISellMessageService iSellMessageService;

    private Logger logger = LoggerFactory.getLogger(SellMessageController.class);

    @ApiOperation("添加卖车预定留言的请求方法")
    @ApiImplicitParam(name = "rentMessageEntity", value = "卖车预定留言实体类", dataType = "RentMessageEntity")
    @RequestMapping("addSellMessage")
    public ResponseData addSellMessage(SellMessageEntity sellMessageEntity, HttpSession session) {
        logger.info("收到addContract请求：" + sellMessageEntity.toString());
        sellMessageEntity.setPersonalId((Integer) session.getAttribute("id"));
        iSellMessageService.addSellMessage(sellMessageEntity);
        logger.info("发送addContract响应：" + ResponseData.ok());
        return ResponseData.ok();
    }

    @ApiOperation("查询买车预定用户信息的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "出售车位id", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "显示当前页面的条数", dataType = "String")
    })
    @RequestMapping("getSellParkingByAll")
    public ResponseData getSellParkingByAll(int id, String page, String size) {
        logger.info("收到getSellParkingByAll的请求:" + "page:" + page + "size:" + size);
        IPage<SellMessageBean> iPage = iSellMessageService.getSellParkingByAll(id, Integer.parseInt(page), Integer.parseInt(size));
        ResponseData responseData = new ResponseData();
        responseData.getData().put("page", iPage);
        logger.info("发送getSellParkingByAll的响应：" + responseData);
        return responseData;
    }
}
