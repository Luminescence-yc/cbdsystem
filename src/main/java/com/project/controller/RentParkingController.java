package com.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.bean.RentParkingBean;
import com.project.entity.RentParkingEntity;
import com.project.service.IPersonalParkingService;
import com.project.service.IRentParkingService;
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
 * @author 朱 骞/陈云龙
 * @version v1.0
 * @ClassName: RentParkingController
 * @Description: 出租车位页面控制器
 * @date 2019年06月03日 10:43
 */
@Api("出租车位页面控制器")
@RestController
@RequestMapping("rentPark")
public class RentParkingController {
    @Autowired
    private IRentParkingService rentParkingService;
    @Autowired
    private IPersonalParkingService iPersonalParkingService;
    private Logger logger = LoggerFactory.getLogger(RentParkingController.class);

    @ApiOperation("动态条件查询出租车位的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginStartTime", value = "开始的租赁开始时间", dataType = "String"),
            @ApiImplicitParam(name = "overStartTime", value = "结束的租赁开始时间", dataType = "String"),
            @ApiImplicitParam(name = "beginEndTime", value = "开始的租赁结束时间", dataType = "String"),
            @ApiImplicitParam(name = "overEndTime", value = "结束的租赁结束时间", dataType = "String"),
            @ApiImplicitParam(name = "address", value = "地址", dataType = "String"),
            @ApiImplicitParam(name = "startRentPrice", value = "开始的出租价格区间", dataType = "String"),
            @ApiImplicitParam(name = "endRentPrice", value = "结束的出租价格区间", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "当前显示的条数", dataType = "String")
    })
    @RequestMapping("showRentParkingInfo")
    public ResponseData showRentParkingInfo(String beginStartTime, String overStartTime,
                                            String beginEndTime, String overEndTime,
                                            String address, String startRentPrice,
                                            String endRentPrice, String page, String size,HttpSession session) {
        logger.info("收到findAllByCondition的请求:" + "beginStartTime:" + beginStartTime + "overStartTime:" + overStartTime +
                "beginEndTime:" + beginEndTime + "overEndTime:" + overEndTime +
                "address:" + address + "startRentPrice:" + startRentPrice + "endRentPrice:" + endRentPrice +
                "page:" + page + "size:" + size);
        if (beginStartTime == null || beginStartTime.length() == 0) {
            beginStartTime = null;
        }
        if (overStartTime == null || overStartTime.length() == 0) {
            overStartTime = null;
        }
        if (beginEndTime == null || beginEndTime.length() == 0) {
            beginEndTime = null;
        }
        if (overEndTime == null || overEndTime.length() == 0) {
            overEndTime = null;
        }
        if (address == null || address.length() == 0) {
            address = null;
        } else {
            address = "%" + address + "%";
        }
        if (startRentPrice == null || startRentPrice.length() == 0) {
            startRentPrice = null;
        }
        if (endRentPrice == null || endRentPrice.length() == 0) {
            endRentPrice = null;
        }
        Map<String, String> map = new HashMap<>(10);
        map.put("beginStartTime", beginStartTime);
        map.put("overStartTime", overStartTime);
        map.put("beginEndTime", beginEndTime);
        map.put("overEndTime", overEndTime);
        map.put("address", address);
        map.put("startRentPrice", startRentPrice);
        map.put("endRentPrice", endRentPrice);
        map.put("page", page);
        map.put("size", size);
        map.put("personalId", (Integer) session.getAttribute("id")+"");
        IPage<RentParkingBean> pages = rentParkingService.showRentParkingInfo(map);
        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.getData().put("pages", pages);
        logger.info("发送findAllByCondition的响应:" + responseData);
        return responseData;
    }

    @ApiOperation("根据出租车位id删除出租车位信息的请求方法")
    @ApiImplicitParam(name = "id", value = "出租车位id", dataType = "int")
    @RequestMapping("deleteRentParking")
    public ResponseData deleteRentParking(int id) {
        logger.info("收到deleteRentParking的请求:" + "id:" + id);
        rentParkingService.deleteRentParking(id);
        logger.info("发送deleteRentParking的响应:" + ResponseData.ok());
        return ResponseData.ok();
    }

    @ApiOperation("查询全部租赁交易的预定用户信息的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "当前登录用户id", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "当前页数显示的条数", dataType = "String")
    })
    @RequestMapping("getAllRentParkingInformation")
    public ResponseData getAllRentParkingInformation(HttpSession session, String page, String size) {
        Integer personalId = (Integer) session.getAttribute("id");
        logger.info("收到getAllRentParkingInformation的请求:" + "当前登录用户id" + "page:" + page + "size:" + size);
        IPage<RentParkingBean> iPage = rentParkingService.getAllRentParkingInformation(personalId, Integer.parseInt(page), Integer.parseInt(size));
        ResponseData responseData = new ResponseData();
        responseData.getData().put("page", iPage);
        logger.info("发送getAllRentParkingInformation的响应:" + responseData);
        return responseData;
    }

    @ApiOperation("查询全部买车交易的预定用户信息的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "当前登录用户id", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "当前页数显示的条数", dataType = "String")
    })
    @RequestMapping("getAllRent")
    public ResponseData getAllRent(HttpSession session, String page, String size) {
        Integer personalId = (Integer) session.getAttribute("id");
        logger.info("收到getAllRent的请求:" + "当前登录用户id" + "page:" + page + "size:" + size);
        IPage<RentParkingBean> iPage = rentParkingService.getAllRent(personalId, Integer.parseInt(page), Integer.parseInt(size));
        ResponseData responseData = new ResponseData();
        responseData.getData().put("page", iPage);
        logger.info("发送getAllRent的响应:" + responseData);
        return responseData;
    }

    @ApiOperation("添加出租车位的请求方法")
    @ApiImplicitParam(name = "rentParkingEntity", value = "出租车位实体类", dataType = "RentParkingEntity")
    @RequestMapping("addRentParking")
    public ResponseData addRentParking(RentParkingEntity rentParkingEntity) {
        logger.info("收到addRentParking请求：" + rentParkingEntity.toString());
        iPersonalParkingService.updatePersonalParkingStatus(rentParkingEntity.getParkingId(), "出租中");
        int i = rentParkingService.addRentParking(rentParkingEntity);
        if (i == 0) {
            logger.info("发送addRentParking响应：" + ResponseData.notFound());
            return ResponseData.notFound();
        }
        logger.info("发送addRentParking响应：" + ResponseData.ok());
        return ResponseData.ok();
    }
}
