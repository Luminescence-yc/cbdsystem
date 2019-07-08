package com.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.bean.RentMessageBean;
import com.project.entity.RentMessageEntity;
import com.project.service.IRentMessageService;
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
 * @author 陈云龙
 * @version v1.0
 * @ClassName:RentMessageController
 * @Description:租车预定留言控制器
 * @date 2019年06月03日 12:26
 */
@Api("租车预定留言页面控制器")
@RestController
@RequestMapping("rentMessage")
public class RentMessageController {
    @Autowired
    private IRentMessageService iRentMessageService;
    private Logger logger = LoggerFactory.getLogger(RentMessageController.class);

    @ApiOperation("添加租车预定留言的请求方法")
    @ApiImplicitParam(name = "rentMessageEntity", value = "租车预定留言实体类", dataType = "RentMessageEntity")
    @RequestMapping("addRentMessage")
    public ResponseData addRentMessage(RentMessageEntity rentMessageEntity, HttpSession session) {
        logger.info("收到addContract请求：" + rentMessageEntity.toString());
        rentMessageEntity.setPersonalId((Integer) session.getAttribute("id"));
        iRentMessageService.addRentMessage(rentMessageEntity);
        logger.info("发送addContract响应：" + ResponseData.ok());
        return ResponseData.ok();
    }

    @ApiOperation("查询车位出租预定留言的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rentPersonalId", value = "出租用户id", dataType = "id"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "当前显示的条数", dataType = "String")
    })
    @RequestMapping("showRentMessageInfo")
    public ResponseData showRentMessageInfo(String page, String size, String rentPersonalId) {
        logger.info("收到findAllByCondition的请求:" + "rentPersonalId:" + rentPersonalId +
                "page:" + page + "size:" + size);
        Map<String, String> map = new HashMap<>(10);
        map.put("rentPersonalId", rentPersonalId);
        map.put("page", page);
        map.put("size", size);
        IPage<RentMessageBean> pages = iRentMessageService.showRentMessageInfo(map);
        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.getData().put("pages", pages);
        logger.info("发送findAllByCondition的响应:" + responseData);
        return responseData;
    }

    @ApiOperation("查询租赁用户预定信息的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "显示当前页面的条数", dataType = "String")
    })
    @RequestMapping("getRentParkingByAll")
    public ResponseData getRentParkingByAll(int rentId, int parkingId, String page, String size) {
        logger.info("收到getRentParkingByAll的请求:" + "page:" + page + "size:" + size);
        IPage<RentMessageBean> iPage = iRentMessageService.getRentParkingByAll(rentId, Integer.parseInt(page), Integer.parseInt(size));
        ResponseData responseData = new ResponseData();
        responseData.getData().put("page", iPage);
        logger.info("发送getRentParkingByAll的响应：" + responseData);
        return responseData;
    }

    @ApiOperation("删除留言和出租车位信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "messageId", value = "出租留言id", dataType = "int"),
            @ApiImplicitParam(name = "rentId", value = "出租车位id", dataType = "int"),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "int")
    })
    @RequestMapping("/deleteMessageAndRentPark")
    public ResponseData deleteRentMessageByRentId(int messageId, int rentId, HttpSession session, int userId) {
        Integer rentPersonId = (Integer) session.getAttribute("id");
        logger.info("收到deleteMessageAndRentPark的请求:" + "message:" + messageId + "rentId:" + rentId + "userId" + userId);
        int i = iRentMessageService.deleteRentMessageByRentId(messageId, rentId, rentPersonId, userId);
        if (i == 0) {
            logger.info("发送deleteMessageAndRentPark的响应:" + ResponseData.notFound());
            return ResponseData.notFound();
        }
        logger.info("发送deleteMessageAndRentPark的响应：" + ResponseData.ok());
        return ResponseData.ok();
    }
}
