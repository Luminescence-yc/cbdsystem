package com.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.bean.PersonalParkingBean;
import com.project.entity.PersonalParkingEntity;
import com.project.service.IPersonalParkingService;
import com.project.service.ISellParkingService;
import com.project.util.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Date;
import java.util.*;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:PersonalParkingController
 * @Description:个人车位页面控制器
 * @date 2019年06月04日 10:25
 */
@Api("个人车位页面控制器")
@RestController
@RequestMapping("personalParking")
public class PersonalParkingController {
    @Autowired
    private IPersonalParkingService iPersonalParkingService;
    @Autowired
    private ISellParkingService iSellParkingService;
    private Logger logger = LoggerFactory.getLogger(PersonalParkingController.class);

    @ApiOperation("动态条件查询个人车位的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "开始的租赁开始时间", dataType = "String"),
            @ApiImplicitParam(name = "startApplyDate", value = "申请开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endApplyDate", value = "申请结束时间", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "当前显示的条数", dataType = "String")
    })
    @RequestMapping("showRentParkingInfo")
    public ResponseData showPersonalParkingInfo(String username, String status, String startApplyDate,
                                                String endApplyDate, String page, String size) {
        logger.info("收到showRentParkingInfo的请求:" + "status:" + status +
                "startApplyDate:" + startApplyDate + "endApplyDate:" + endApplyDate +
                "page:" + page + "size:" + size);
        if (username.length() == 0) {
            username = null;
        }
        if (status.length() == 0) {
            status = null;
        }
        if (startApplyDate.length() == 0) {
            startApplyDate = null;
        }
        if (endApplyDate.length() == 0) {
            endApplyDate = null;
        }
        Map<String, String> map = new HashMap<>(10);
        map.put("username", username);
        map.put("status", status);
        map.put("startApplyDate", startApplyDate);
        map.put("endApplyDate", endApplyDate);
        map.put("page", page);
        map.put("size", size);
        IPage<PersonalParkingBean> pages = iPersonalParkingService.showPersonalParkingInfo(map);
        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.getData().put("pages", pages);
        logger.info("发送findAllByCondition的响应:" + responseData);
        return responseData;
    }

    @ApiOperation("根据用户Id查询个人车位的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "当前显示的条数", dataType = "String")
    })
    @RequestMapping("showRentParkingInfoByPersonalId")
    public ResponseData showPersonalParkingInfo(HttpSession session, String page, String size) {
        logger.info("收到showRentParkingInfoByPersonalId的请求:" + "page:" + page + "size:" + size);
        Map<String, String> map = new HashMap<>(10);
        map.put("page", page);
        map.put("size", size);
        IPage<PersonalParkingBean> pages = iPersonalParkingService.showPersonalParkingInfoPersonalId(map, (Integer) session.getAttribute("id"));
        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.getData().put("pages", pages);
        logger.info("发送showRentParkingInfoByPersonalId的响应:" + responseData);
        return responseData;
    }

    @ApiOperation("添加个人车位的请求方法")
    @ApiImplicitParam(name = "personalParkingEntity", value = "出租车位实体类", dataType = "PersonalParkingEntity")
    @RequestMapping("addPersonalParking")
    public ResponseData addPersonalParking(HttpSession session, HttpServletRequest request) {
        logger.info("收到addRentParking请求：" + request.toString());
        PersonalParkingEntity personalParkingEntity = new PersonalParkingEntity();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        Map<String, String> map = new HashMap<>();
        try {
            List<FileItem> list = servletFileUpload.parseRequest(request);
            for (FileItem fileItem : list) {
                if (fileItem.isFormField()) {
                    map.put(fileItem.getFieldName(), new String(fileItem.getString().getBytes("iso8859-1"), "utf-8"));

                    System.out.println(fileItem.getFieldName() + "        " + fileItem.getString().getBytes("iso8859-1"));
                } else {
                    String oldName = fileItem.getName();
                    System.out.println(oldName);
                    String ext = oldName.substring(oldName.lastIndexOf("."));
                    Thread.sleep(1000);
                    String newFileName = UUID.randomUUID() + ext;
                    personalParkingEntity.setPropertyImage(newFileName);
                    fileItem.write(new File("D:\\code\\code\\cbdsystem\\src\\main\\resources\\static\\images\\" + newFileName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        personalParkingEntity.setPersonalId((Integer) session.getAttribute("id"));
        personalParkingEntity.setPropertyNum(map.get("propertyNum"));
        personalParkingEntity.setAddress(map.get("address"));
        personalParkingEntity.setAreaNum(map.get("areaNum"));
        personalParkingEntity.setParkingNum(map.get("parkingNum"));
        Date date = new Date(System.currentTimeMillis());
        personalParkingEntity.setApplyDate(date.toString());
        int i = iPersonalParkingService.addPersonalParking(personalParkingEntity);
        if (i == 0) {
            logger.info("发送addRentParking响应：" + ResponseData.notFound());
            return ResponseData.notFound();
        }
        logger.info("发送addRentParking响应：" + ResponseData.ok());
        return ResponseData.ok();
    }

    @ApiOperation("根据id修改个人车位状态的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "个人车位id", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "个人车位状态", dataType = "String"),
    })
    @RequestMapping("updatePersonalParkingStatus")
    public ResponseData updatePersonalParkingStatus(int id, String status) {
        logger.info("收到addRentParking请求：" + "id :" + id + "status:" + status);
        int x = 0;
        if (status.equals("出售中")) {
            x = iPersonalParkingService.shelvesSellParkingByParkingId(id);
        } else {
            x = iPersonalParkingService.shelvesRentParkingByParkingId(id);
        }
        int i = iPersonalParkingService.updatePersonalParkingStatus(id, "未发布");
        if (i == 0 && x == 0) {
            logger.info("发送addRentParking响应：" + ResponseData.notFound());
            return ResponseData.notFound();
        }
        logger.info("发送addRentParking响应：" + ResponseData.ok());
        return ResponseData.ok();
    }

    @ApiOperation("根据个人车位id查找车位对象")
    @ApiImplicitParam(name = "personalParkingId", value = "个人车位id", dataType = "int")
    @RequestMapping("getPersonalParkingAndPersonalByPersonalParkingId")
    public ResponseData getPersonalParkingAndPersonalByPersonalParkingId(int personalParkingId) {
        logger.info("收到getPersonalParkingAndPersonalByPersonalParkingId请求："
                + "personalParkingId :" + personalParkingId);
        PersonalParkingBean parkingBean = iPersonalParkingService
                .getPersonalParkingAndPersonalByPersonalParkingId(personalParkingId);
        ResponseData responseData = null;
        if (parkingBean != null) {
            responseData = ResponseData.ok();
            responseData.getData().put("parkingBean", parkingBean);

        } else {
            responseData = ResponseData.notFound();
        }
        logger.info("发送getPersonalParkingAndPersonalByPersonalParkingId响应：" + responseData);
        return responseData;
    }

}
