package com.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.ComplaintBean;
import com.project.bean.PersonalBean;
import com.project.entity.ComplaintEntity;
import com.project.entity.MessageEntity;
import com.project.entity.PersonalEntity;
import com.project.entity.ResponseTimeEntity;
import com.project.service.IComplaintService;
import com.project.service.IRentHistoryService;
import com.project.util.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.Date;

/**
 * @author 杨彪
 * @ClassName: 投诉受理页面控制器
 * @Description:
 * @date 2019年05月31日 16:41
 */
@Api("投诉受理页面控制器")
@RestController
@RequestMapping("/complaint/")
public class ComplaintController {
    @Autowired
    private IComplaintService complaintService;

    private Logger logger = LoggerFactory.getLogger(ContractController.class);

    @ApiOperation("分页查询投诉受理的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "当前显示的条数", dataType = "String")
    })
    @RequestMapping(value = "findComplaintByStatus", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData findComplaintByStatus(int currentPage, int pageSize) {
        logger.info("收到findComplaintByStatus请求：" + "page:" + currentPage + "size:" + pageSize);
        ResponseData responseData = new ResponseData();
        IPage<ComplaintBean> page = complaintService.findComplaintByStatus(currentPage, pageSize);
        responseData.getData().put("page", page);
        logger.info("发送findComplaintByStatus的响应:" + responseData);
        return responseData;
    }

    @ApiOperation("根据id查询投诉受理的请求方法")
    @ApiImplicitParam(name = "id", value = "投诉受理主键Id", dataType = "Int")
    @RequestMapping(value = "findById", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData findById(int id) {
        logger.info("收到findById请求：" + "id:" + id);
        ResponseData responseData = new ResponseData();
        ComplaintBean complaintBean = complaintService.findById(id);
        responseData.getData().put("complaint", complaintBean);
        logger.info("发送findComplaintByStatus的响应:" + responseData);
        return responseData;
    }

    @ApiOperation("根据id修改投诉受理状态的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "投诉受理主键Id", dataType = "Int"),
            @ApiImplicitParam(name = "status", value = "投诉受理的状态", dataType = "String")
    })
    @RequestMapping(value = "updateStatus", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData updateStatus(int id, String status) {
        logger.info("收到findById请求：" + "id:" + id + "status:" + status);
        complaintService.updateStatus(id, status);
        logger.info("发送findComplaintByStatus的响应:" + ResponseData.ok());
        return ResponseData.ok();
    }

    @ApiOperation("添加投诉受理的请求方法")
    @ApiImplicitParam(name = "complaintEntity", value = "投诉受理实体类", dataType = "complaintEntity")
    @RequestMapping(value = "addComplaint", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData addComplaint(String complaintReason, HttpSession httpSession, int userId,int byId) {
        ComplaintBean complaintBean = new ComplaintBean();
        complaintBean.setComplaintReason(complaintReason);
        logger.info("addComplaint：" + "complaintEntity:" + complaintBean);
        int id = (int) httpSession.getAttribute("id");
        complaintBean.setStatus("受理中");
        complaintBean.setComplaintDate(new Date(System.currentTimeMillis()) + "");
        //被投诉者id
        complaintBean.setPersonalByUpholdingId(byId);
        //投诉者id
        complaintBean.setPersonalComplainantId(id);
        //出租记录id
        complaintBean.setRenthistoryId(userId);
        complaintService.addComplaint(complaintBean);
        logger.info("发送findComplaintByStatus的响应:" + ResponseData.ok());
        return ResponseData.ok();

    }
}
