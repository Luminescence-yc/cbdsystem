package com.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CompanyContractBean;
import com.project.service.ICompanyContractService;
import com.project.util.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName:CompanyContractConterller
 * @Description:租户合约控制器
 * @date 2019年06月04日 15:21
 */
@Api("租户合约控制器")
@RestController
@RequestMapping("/companyContract")
public class CompanyContractConterller {
    @Autowired
    private ICompanyContractService contractService;
//    @Autowired
//    private ICompanyService companyService;
    private Logger logger = LoggerFactory.getLogger(ContractController.class);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "CompanyContractEntity", value = "租户合约实体类", dataType = "CompanyContractEntity")
    })
    @RequestMapping(value = "addCompanyContract",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addCompanyContract(CompanyContractBean contractBean, @RequestParam("cbdParkingId") List<Integer> list) {
        contractBean.setCbdParkingId(list);
        int result = contractService.addCompanyContract(contractBean);
        if (result != 0) {
            logger.info("发送addCompanyContract响应：" + ResponseData.ok());
            return ResponseData.ok();
        }
        logger.info("发送addCompanyContract响应：" + ResponseData.notFound());
        return ResponseData.notFound();
    }

    @ApiImplicitParam(name = "id", value = "租户合约编号", dataType = "int")
    @RequestMapping("find")
    public ResponseData findById(int id) {
        logger.info("收到find的请求:" + "id:" + id);
        CompanyContractBean contractBean = contractService.findById(id);
        if (contractBean != null) {
            ResponseData responseData = new ResponseData();
            responseData.setCode(200);
            responseData.getData().put("contractBean", contractBean);
            logger.info("发送find的响应:" + responseData);
            return responseData;
        }
        return ResponseData.notFound();
    }

    @ApiImplicitParam(name = "CompanyContractEntity", value = "租户合约实体类", dataType = "CompanyContractEntity")
    @RequestMapping("updates")
    public ResponseData update(CompanyContractBean companyContractBean) {
        logger.info("收到updates的请求:" + "companyContractBean:" + companyContractBean);
        int result = contractService.update(companyContractBean);
        if (result != 0) {
            ResponseData responseData = new ResponseData();
            responseData.setCode(200);
            logger.info("发送updates的响应:" + responseData);
            return responseData;
        }
        return ResponseData.notFound();
    }

    @ApiImplicitParam(name = "id", value = "id", dataType = "int")
    @RequestMapping("deletes")
    public ResponseData delete(int id) {
        logger.info("收到deletes请求:" + "id:" + id);
        int result = contractService.delete(id);
        if (result != 0) {
            ResponseData responseData = new ResponseData();
            responseData.setCode(200);
            logger.info("发送deletes的响应:" + responseData);
            return responseData;
        }
        return ResponseData.notFound();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyName", value = "合同单位", dataType = "String"),
            @ApiImplicitParam(name = "address", value = "车位地址", dataType = "String"),
            @ApiImplicitParam(name = "companyPrice", value = "成交价格", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "每页显示页数", dataType = "String"),
    })
    @RequestMapping("findByCondition")
    public ResponseData findByCondition(String companyName, String contractNum, String companyPrice, String page, String size) {
        logger.info("收到findByCondition的请求:" + "companyName:" + companyName + "contractNum:" + contractNum +
                "companyPrice:" + companyPrice + "page:" + page + "size:" + size);
        Map<String, String> condition = new HashMap<>(5);
        if (null != companyName && companyName.trim().length() != 0) {
            condition.put("companyName", companyName);
        }
        if (null != contractNum && contractNum.trim().length() != 0) {
            condition.put("contractNum", contractNum);
        }
        if (null != companyPrice && companyPrice.trim().length() != 0) {
            condition.put("companyPrice", companyPrice);
        }
        condition.put("page", page);
        condition.put("size", size);
        Page<CompanyContractBean> pages = contractService.findCompanyContractByCondition(condition);
        ResponseData responseData = null;
        if (pages != null) {
            responseData = ResponseData.ok();
            responseData.getData().put("pages", pages);
        } else {
            responseData = ResponseData.notFound();
        }
        logger.info("发送findCondition的响应:" + responseData);
        return responseData;
    }

    @ApiOperation("动态条件查询企业的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyName",value = "企业名称",dataType = "String"),
            @ApiImplicitParam(name = "username",value = "企业用户名",dataType = "String"),
            @ApiImplicitParam(name = "floor",value = "楼层位置",dataType = "String"),
            @ApiImplicitParam(name = "userId",value = "用户,id",dataType = "String"),
            @ApiImplicitParam(name = "contactPerson",value = "联系人",dataType = "String"),
            @ApiImplicitParam(name = "tel",value = "联系电话",dataType = "String"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "当前显示的条数", dataType = "String")
    })
    @RequestMapping(value = "findCompanyByCondition",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseData findCompanyByCondition(String userId, String companyName, String username, String floor, String
            contactPerson, String tel, @RequestParam(value="page", defaultValue="1") String page, @RequestParam(value="size", defaultValue="10")String size){
        logger.info("收到findCompanyByCondition请求："+"userId:"+userId+"companyName:"+companyName+"username:"+username+"floor:"+floor+
                "contactPerson:"+contactPerson+"tel:"+tel+"page:"+page+"size:"+size);
        System.out.println(page+"   "+size);
        if (companyName==null||companyName.length() == 0) {
            companyName=null;
        }
        if (userId==null||userId.length() == 0) {
            userId=null;
        }
        if (username==null|| username.length() == 0) {
            username=null;
        }
        if (floor==null||floor.length() == 0) {
            floor=null;
        }
        if (contactPerson==null||contactPerson.length() == 0) {
            contactPerson=null;
        }
        if ( tel==null||tel.length() == 0) {
            tel=null;
        }
        Map<String, String> map = new HashMap<>(10);
        map.put("companyName", companyName);
        map.put("username", username);
        map.put("floor", floor);
        map.put("contactPerson", contactPerson);
        map.put("tel", tel);
        map.put("userId",userId);
        map.put("page", page);
        map.put("size", size);
//        Page<CompanyContractBean> pages = contractService.
        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
//        responseData.getData().put("pages", pages);
        logger.info("发送findCompanyByCondition的响应:" + responseData);
        return responseData;
    }
//
//    @ApiOperation("登录用户信息查询方法")
//    @ApiImplicitParam(name = "session", value = "企业session", dataType = "HttpSession")
//    @RequestMapping("showAdminMessage")
//    public ResponseData showAdminMessage(HttpSession session){
//        logger.info("收到findAdminById请求：" + "session:" + session);
//        Integer id = (Integer) session.getAttribute("id");
//        UserBean userBean = (UserBean) session.getAttribute("user");
//        CompanyBean companyBean= companyService.findCompanyById(id);
//        ResponseData responseData = new ResponseData();
//        responseData.getData().put("companyBean",companyBean);
//        logger.info("发送findAdminById的响应:" + responseData);
//        return responseData;
//    }

    @ApiOperation("动态条件查询合同的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate",value = "开始日期",dataType = "String"),
            @ApiImplicitParam(name = "endDate",value = "结束日期",dataType = "String"),
            @ApiImplicitParam(name = "companyId",value = "登录的企业ID",dataType = "String"),
            @ApiImplicitParam(name = "page", value =
                    "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "当前显示的条数", dataType = "String")
    })
    @RequestMapping(value = "findByCompanyIdAndStartAndEndDate",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseData findByCompanyIdAndStartAndEndDate(HttpSession session,String startDate, String endDate, @Param("companyId") String companyId, @RequestParam(value="page", defaultValue="1") String page, @RequestParam(value="size", defaultValue="10")String size) throws Exception {
        logger.info("收到findCompanyByCondition请求："+"startDate:"+startDate+"endDate:"+endDate+"companyId:"+companyId+"page:"+page+"size:"+size);
        System.out.println(page+"   "+size);
        if (startDate==null||startDate.length() == 0) {
            startDate=null;
        }
        if (endDate==null||endDate.length() == 0) {
            endDate=null;
        }
        Map<String, String> map = new HashMap<>(10);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("companyId",(Integer)session.getAttribute("id")+"");
        map.put("page", page);
        map.put("size", size);
        Page<CompanyContractBean> pages = contractService.findByCompanyIdAndStartAndEndDate(map);
        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.getData().put("pages", pages);
        logger.info("发送findCompanyByCondition的响应:" + responseData);
        return responseData;
    }
}
