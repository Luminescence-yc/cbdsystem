package com.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.SellParkingBean;
import com.project.bean.SellParkingPageBean;
import com.project.service.IPersonalParkingService;
import com.project.service.ISellParkingService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 费宇
 * @version v1.0
 * @ClassName: SellParkingController
 * @Description: 出售车位 页面控制器类
 * @date 2019年06月05日 09:37
 */
@Api("出售车位页面控制器")
@RestController
@RequestMapping("sellParking")
public class SellParkingController {
    @Autowired
    private ISellParkingService sellParkingService;
    @Autowired
    private IPersonalParkingService iPersonalParkingService;
    /**
     * 获取日志记录对象
     */
    private Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @ApiOperation("主页买卖车位及动态查询方法")
    @ApiImplicitParam(name = "pageBean", value = "分页参数与查询参数", dataType = "SellParkingPageBean")
    @RequestMapping("showSellParkingEntityInfo")
    public ResponseData showSellParkingEntityInfo(SellParkingPageBean pageBean,HttpSession session) {
        logger.info("收到showSellParkingEntityInfo(主页买卖车位)的请求，参数对象为：" + pageBean);
        Map<String, String> condition = new HashMap<>(10);
        String startTime = pageBean.getStartTime();
        String endTime = pageBean.getEndTime();
        String address = pageBean.getAddress();
        String startSellPrice = pageBean.getStartSellPrice();
        String endSellPrice = pageBean.getEndSellPrice();
        if (null != startTime && startTime.trim().length() != 0) {
            condition.put("startTime", startTime);
        }
        if (null != endTime && endTime.trim().length() != 0) {
            condition.put("endTime", endTime);
        }
        if (null != address && address.trim().length() != 0) {
            condition.put("address", pageBean.getAddress());
        }
        if (null != startSellPrice && startSellPrice.trim().length() != 0) {
            condition.put("startSellPrice", pageBean.getStartSellPrice());
        }
        if (null != endSellPrice && endSellPrice.trim().length() != 0) {
            condition.put("endSellPrice", pageBean.getEndSellPrice());
        }
        condition.put("personalId", (Integer) session.getAttribute("id")+"");
        condition.put("current", pageBean.getCurrent());
        condition.put("size", pageBean.getSize());
        condition.put("sellStatus", "出售中");
        Page<SellParkingBean> sellParkingBeanPage = sellParkingService.showSellParkingEntityInfo(condition);
        ResponseData responseData = this.isSellParkingPageByStatus(sellParkingBeanPage);
        logger.info("响应showSellParkingEntityInfo(主页买卖车位)的请求，响应对象为：" + responseData);
        return responseData;
    }

    @ApiOperation("出售车位信息添加方法")
    @ApiImplicitParam(name = "sellParkingBean", value = "添加的bean参数", dataType = "SellParkingBean")
    @RequestMapping("addSellParking")
    public ResponseData addSellParking(SellParkingBean sellParkingBean) {
        logger.info("收到addSellParking(出售车位信息添加)的请求，参数对象为：" + sellParkingBean);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        sellParkingBean.setReleaseTime(df.format(new Date()));
        iPersonalParkingService.updatePersonalParkingStatus(sellParkingBean.getParkingId(), "出售中");
        ResponseData responseData = sellParkingService.addSellParking(sellParkingBean) != 0
                ? ResponseData.ok() : ResponseData.notFound();
        logger.info("响应addSellParking(出售车位信息添加)的请求，响应对象为：" + responseData);
        return responseData;
    }

    @ApiOperation("查询出售车位信息方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personalId", value = "出售用户ID", dataType = "int"),
            @ApiImplicitParam(name = "current", value = "当前页数", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页条数", dataType = "int")
    })
    @RequestMapping(value = "getSellParkingByPage")
    public ResponseData getSellParkingByPage(HttpSession session, int current, int size) {
        logger.info("收到getSellParkingByPage(查询出售车位信息)的请求，参数personalId为："
                + (Integer) session.getAttribute("id") + "; current（当前页数）为：" + current + "；size（每页条数）为：" + size);
        Page<SellParkingBean> sellParkingBeanPage =
                sellParkingService.getSellParkingByPage((Integer) session.getAttribute("id"), current, size);
        ResponseData responseData = this.isSellParkingPageByStatus(sellParkingBeanPage);
        logger.info("响应getSellParkingByPage(查询出售车位信息)的请求，参数对象为：" + responseData);
        return responseData;
    }

    @ApiOperation("出售车位信息删除方法")
    @ApiImplicitParam(name = "id", value = "出售车位信息的ID", dataType = "int")
    @RequestMapping(value = "deleteSellParkingById")
    public ResponseData deleteSellParkingById(int id) {
        logger.info("收到deleteSellParkingById(删除出售车位信息)的请求，参数对象为：" + id);
        ResponseData responseData = sellParkingService.deleteSellParkingById(id) != 0
                ? ResponseData.ok() : ResponseData.notFound();
        logger.info("响应deleteSellParkingById(删除出售车位信息)的请求，响应对象为：" + responseData);
        return responseData;
    }

    @ApiOperation("查询购买车位信息方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personalId", value = "购买用户ID", dataType = "int"),
            @ApiImplicitParam(name = "current", value = "当前页数", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页条数", dataType = "int")
    })
    @RequestMapping("getBuyParkingByPage")
    public ResponseData getBuyParkingByPage(HttpSession session, int current, int size) {
        logger.info("收到getBuyParkingByPage(查询购买车位信息)的请求，参数personalId为："
                + (Integer) session.getAttribute("id") + "; current（当前页数）为：" + current + "；size（每页条数）为：" + size);
        Page<SellParkingBean> sellParkingBeanPage =
                sellParkingService.getBuyParkingByPage((Integer) session.getAttribute("id"), current, size);
        ResponseData responseData = this.isSellParkingPageByStatus(sellParkingBeanPage);
        logger.info("响应getBuyParkingByPage(查询购买车位信息)的请求，参数对象为：" + responseData);
        return responseData;
    }

    /**
     * 业务方法，判断分页对象状态
     *
     * @param sellParkingBeanPage 出售车位分页对象
     * @return 返回ResponseData对象
     */
    private ResponseData isSellParkingPageByStatus(Page<SellParkingBean> sellParkingBeanPage) {
        ResponseData responseData = null;
        if (sellParkingBeanPage != null) {
            responseData = ResponseData.ok();
            responseData.getData().put("page", sellParkingBeanPage);
        } else {
            responseData = ResponseData.notFound();
        }
        return responseData;
    }
}
