package com.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CbdParkingBean;
import com.project.entity.CbdParkingEntity;
import com.project.service.ICbdParkingService;
import com.project.util.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName:CbdParkingController
 * @Description:cbd车位控制器
 * @date 2019年06月03日 9:09
 */
@RestController
@Api("cbd车位管理控制器")
@RequestMapping(value = "/cbdParking")
public class CbdParkingController {
    @Autowired
    private ICbdParkingService cbdParkingService;
    private Logger logger = LoggerFactory.getLogger(ContractController.class);

    @ApiImplicitParam(name = "id", value = "cbd车位管理编号", dataType = "int")
    @RequestMapping("find")
    public ResponseData findById(int id) {
        logger.info("收到find的请求:" + "id:" + id);
        CbdParkingBean cbdParkingBean = cbdParkingService.findById(id);
        if (cbdParkingBean != null) {
            ResponseData responseData = new ResponseData();
            responseData.setCode(200);
            responseData.getData().put("cbdParkingBean", cbdParkingBean);
            logger.info("发送findById的响应:" + responseData);
            return responseData;
        }
        return ResponseData.unauthorized();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaNum", value = "车位区域", dataType = "String"),
            @ApiImplicitParam(name = "address", value = "车位地址", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "车位状态", dataType = "String"),
            @ApiImplicitParam(name = "companyId", value = "企业编号", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "当前显示的条数", dataType = "String"),
            @ApiImplicitParam(name = "companyId", value = "企业编号", dataType = "String")
    })
    @RequestMapping("findCondition")
    public ResponseData findByCondition(String areaNum, String address, String status, String companyId, String page, String size) {
        logger.info("收到findCondition的请求:" + "areaNum:" + areaNum + "address:" + address +
                "status:" + status + "page:" + page + "size:" + size);
        Map<String, String> condition = new HashMap<>(10);
        if (areaNum != null && areaNum.length() != 0) {
            condition.put("areaNum", areaNum);
        }
        if (address != null && address.length() != 0) {
            condition.put("address", address);
        }
        if (status != null && status.length() != 0) {
            condition.put("status", status);
        }
        if (companyId != null) {
            condition.put("companyId", companyId);
        }
        condition.put("page", page);
        condition.put("size", size);
        Page<CbdParkingBean> pages = cbdParkingService.findCbdParkingByCondition(condition);
        if (pages != null) {
            ResponseData responseData = new ResponseData();
            responseData.setCode(200);
            responseData.getData().put("pages", pages);
            logger.info("发送findCondition的响应:" + responseData);
            return responseData;
        }
        return ResponseData.notFound();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "CbdParkingEntity", value = "cbd车位实体类", dataType = "CbdParkingEntity")
    })
    @RequestMapping("addParking")
    public ResponseData addParking(CbdParkingBean cbdParkingBean) {
        logger.info("收到addParking的请求:" + "cbdParkingBean:" + cbdParkingBean.toString());
        int result = cbdParkingService.addParking(cbdParkingBean);
        if (result != 0) {
            ResponseData responseData = new ResponseData();
            responseData.setCode(200);
            responseData.setMessage("ok");
            logger.info("发送addParking响应：");
            return responseData;
        }
        logger.info("发送addParking响应：" + ResponseData.notFound());
        return ResponseData.notFound();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "车位地址", dataType = "String"),
            @ApiImplicitParam(name = "areaNum", value = "车位区域", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "显示分页数", dataType = "String"),
    })
    @RequestMapping("findByArea")
    public ResponseData findByArea(String address, String areaNum, int page, int size) {
        logger.info("收到findByArea的请求:" + "address:" + address + "areaNum:" + areaNum +
                "page:" + page + "size:" + size);
        try {
            Page<CbdParkingBean> page1 = cbdParkingService.findCbdParkingByArea(address, areaNum, page, size);
            if (page1 != null) {
//        System.out.println(page1);
                ResponseData responseData = new ResponseData();
                responseData.setCode(200);
                responseData.getData().put("pages", page1);
                logger.info("发送findByArea的响应:" + responseData);
                return responseData;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseData.notFound();
    }

    /**
     * 查找所有车位的地址集合
     *
     * @return 地址集合
     */

    @RequestMapping("findAllAddress")
    public ResponseData findAllAddress() {
        logger.info("收到findAllAddress的请求:");
        List<String> list = cbdParkingService.findAllAddress();
        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.getData().put("list", list);
        logger.info("发送findCondition的响应:" + responseData);
        return responseData;
    }

    /**
     * 通过地址查找区域集合
     *
     * @param address 车位地址
     * @return 区域集合
     * @throws Exception
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "车位地址", dataType = "String"),
    })
    @RequestMapping("findAreaByAddress")
    public ResponseData findAreaByAddress(String address) throws Exception {
        logger.info("收到findAreaByAddress的请求:" + "address:" + address);
        List<String> list = cbdParkingService.findAreaByAddress(address);
        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.getData().put("list", list);
        logger.info("发送findCondition的响应:" + responseData);
        return responseData;
    }

    /**
     * 通过地址和区域查找车位集合
     *
     * @param address 车位地址
     * @param area    区域编号
     * @param page    当前页数
     * @param size    每页条数
     * @return 分页对象集合
     * @throws Exception
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "车位地址", dataType = "String"),
            @ApiImplicitParam(name = "area", value = "区域编号", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页条数", dataType = "int")
    })
    @RequestMapping("findCbdParkingByArea")
    public ResponseData findCbdParkingByArea(String address, String area, int page, int size) throws Exception {
        logger.info("收到findAreaByAddress的请求:" + "address:" + address + "area:" + area + "page:" + page + "size:" + size);
        Page<CbdParkingBean> pages = cbdParkingService.findCbdParkingByArea(address, area, page, size);
        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.getData().put("pages", pages);
        logger.info("发送findCondition的响应:" + responseData);
        return responseData;
    }

}
