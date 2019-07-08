package com.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CompanyBean;
import com.project.bean.ContractBean;
import com.project.entity.CompanyEntity;
import com.project.service.ICompanyService;
import com.project.util.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘再桦
 * @version v1.0
 * @ClassName
 * @Description
 * @date 2019年05月31日 16:57
 */
@Api("企业管理页面控制器")
@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private ICompanyService companyService;
    private Logger logger = LoggerFactory.getLogger(ContractController.class);

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
    public ResponseData findCompanyByCondition(String userId,String companyName, String username, String floor, String
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
        Page<CompanyBean> pages = companyService.findCompanyByCondition(map);
        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.getData().put("pages", pages);
        logger.info("发送findCompanyByCondition的响应:" + responseData);
        return responseData;
    }

    @ApiOperation("根据id查询企业信息")
    @ApiImplicitParam(name = "id",value = "企业主键Id",dataType = "Int")
    @RequestMapping("findCompanyById")
    public ResponseData findCompanyById(int id){
        logger.info("收到findCompanyById请求："+"id:"+id);
        ResponseData responseData = new ResponseData();
        CompanyBean companyBean = companyService.findCompanyById(id);
        responseData.getData().put("company",companyBean);
        logger.info("发送findCompanyByCondition的响应："+responseData);
        return responseData;
    }

    @ApiOperation("添加企业的请求方法")
    @ApiImplicitParam(name = "companyEntity", value = "企业实体类", dataType = "CompanyEntity")
    @RequestMapping(value = "addCompany",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseData addCompany(CompanyBean companyBean){
        logger.info("收到addCompany请求："+companyBean.toString());
        int i = companyService.addCompany(companyBean);
        if (i == 0) {
            logger.info("发送addCompany响应：" + ResponseData.notFound());
            return ResponseData.notFound();
        }
        logger.info("发送addCompany响应：" + ResponseData.ok());
        return ResponseData.ok();    }

    @ApiOperation("通过企业id修改企业、用户属性的请求方法")
    @ApiImplicitParam(name = "companyEntity", value = "企业实体类", dataType = "CompanyEntity")
    @RequestMapping(value = "updateCompany",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseData updateCompany(CompanyBean companyBean){
        logger.info("收到updateCompany请求"+"companyBean:"+companyBean);
        companyService.updateCompany(companyBean);
        logger.info("发送updateCompany的响应:" + ResponseData.ok());
        return ResponseData.ok();
    }

    @ApiOperation("根据企业主键id删除企业的请求方法")
    @ApiImplicitParam(name = "id",value = "企业id",dataType = "int")
    @RequestMapping(value = "deleteCompany",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseData deleteCompany(int id){
        logger.info("收到deleteCompany的请求:"+"id:"+id);
        companyService.deleteCompany(id);
        logger.info("发送deleteCompany的响应:"+ResponseData.ok());
        return ResponseData.ok();
    }
}
