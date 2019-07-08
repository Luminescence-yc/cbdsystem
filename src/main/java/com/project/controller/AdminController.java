package com.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.AdminBean;
import com.project.bean.RoleBean;
import com.project.bean.UserBean;
import com.project.service.IAdminService;
import com.project.util.ResponseData;
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
 * @author 庞培波
 * @version 1.0
 * @ClassName  AdminController
 * @Description  管理员控制器
 * @date 2019年06月03日13:50
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    private Logger logger = LoggerFactory.getLogger(ContractController.class);

    @ApiOperation("查询所有管理员方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "每页显示信息条数", dataType = "String"),
    })
    @RequestMapping("getAdminNotAll")
    public ResponseData getAdminNotAll(String page,String size) {
            logger.info("收到getAdminNotAll请求：" + "page:" + page + "size:" + size);
            Map<String,String> condition=new HashMap<>(3);
            condition.put("page",page);
            condition.put("size",size);
            Page<AdminBean> pages = adminService.findAdminByAll(condition);
            ResponseData responseData = new ResponseData();
            responseData.getData().put("page", pages);
            logger.info("发送getAdminNotAll的响应:" + responseData);
            return responseData;
    }



    @ApiOperation("管理员添加方法")
    @ApiImplicitParam(name = "adminBean", value = "管理员bean", dataType = "AdminBean")
    @RequestMapping("addAdmin")
    public ResponseData addAdmin(AdminBean adminBean){
        logger.info("收到addAdmin请求：" + adminBean.toString());
      String [] permission={ adminBean.getUserAdmin(), adminBean.getParkingAdmin()
              ,adminBean.getContractAdmin(),adminBean.getComplainAdmin()};
        for (int i = 0; i < permission.length ; i++) {
            if(!("-1".equals(permission[i]))){
                RoleBean roleBean=new RoleBean();
                roleBean.setId(i+3);
                adminBean.getList().add(roleBean);
            }
        }

        if(adminService.addAdmin(adminBean)!=0){
            logger.info("发送addAdmin的响应：" + ResponseData.ok());
            return new ResponseData(200,"管理员添加成功！");
        }else{
            logger.info("发送addAdmin的响应：" + ResponseData.notFound());
            return new ResponseData(500,"管理员添加失败！");
        }
    }

    @ApiOperation("管理员修改方法")
    @ApiImplicitParam(name = "adminBean", value = "管理员实体bean", dataType = "AdminBean")
    @RequestMapping("updateAdmin")
    public ResponseData updateAdmin(AdminBean adminBean){
        logger.info("收到updateAdmin请求：" + adminBean.toString());
        String [] permission={ adminBean.getUserAdmin(), adminBean.getParkingAdmin()
                ,adminBean.getContractAdmin(),adminBean.getComplainAdmin()};
        for (int i = 0; i < permission.length ; i++) {

            System.out.println("收到请求权限数据：=====》》》》"+permission[i]);
            if(!("-1".equals(permission[i]))){
                RoleBean roleBean=new RoleBean();
                roleBean.setId(i+3);
                adminBean.getList().add(roleBean);
            }
        }
        System.out.println("收到adminBean：=====》》》》"+adminBean.toString());
        if(adminService.updateRole(adminBean)!=0){
            logger.info("发送updateAdmin的响应：" + ResponseData.ok());
            return new ResponseData(200,"管理员修改成功！");
        }else{
            logger.info("发送updateAdmin的响应：" + ResponseData.notFound());
            return new ResponseData(500,"管理员修改失败！");
        }
    }

    @ApiOperation("根据管理员id修改电话号码和密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminBean", value = "管理员实体bean", dataType = "AdminBean"),
    })
    @RequestMapping("updateAdminTelById")
    public ResponseData updateAdminTelById(String tel,String password,HttpSession session){
        logger.info("收到findAdminById请求：" + "session:" + session);
        Integer id = (Integer) session.getAttribute("id");
        UserBean userBean = (UserBean) session.getAttribute("user");
        AdminBean  adminBean= adminService.findAdminById(id,userBean.getId());
//        AdminBean  adminBean= new AdminBean();
//        adminBean.setId(id);
//        adminBean.setUserId(userBean.getId());
//        adminBean.setUsername(userBean.getUsername());
        if (tel != null){
            adminBean.setTel(tel);
        }
        if (password != null){
            adminBean.setPassword(password);
        }
        adminBean.setUserId(userBean.getId());
        System.out.println(adminBean.toString());
        logger.info("收到updateAdminTelById请求：" + adminBean.toString());
        if (adminService.updateAdmin(adminBean) != 0){
            logger.info("发送updateAdminTelById的响应：" + ResponseData.ok());
            return new ResponseData(200,"修改成功！");
        }else {
            logger.info("发送updateAdminTelById的响应：" + ResponseData.notFound());
            return new ResponseData(500,"修改失败！");
        }
    }


    @ApiOperation("管理员删除方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员id", dataType = "int"),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "int"),
    })
    @RequestMapping("deleteAdmin")
    public ResponseData deleteAdmin(int id,int userId){
        logger.info("收到deleteAdmin请求：" + "id:" + id + "userId:" + userId);
        if(adminService.deleteAdmin(id,userId)!=0){
            logger.info("发送deleteAdmin的响应：" + ResponseData.ok());
            return new ResponseData(200,"ok");
        }else{
            logger.info("发送deleteAdmin的响应：" + ResponseData.notFound());
            return new ResponseData(500,"no Found");
        }
    }


    @ApiOperation("管理员单个查询方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员id", dataType = "int"),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "int"),
    })
    @RequestMapping("findAdminById")
    public ResponseData findAdminById(int id,int userId){
        logger.info("收到findAdminById请求：" + "id:" + id + "userId:" + userId);
        AdminBean  adminBean= adminService.findAdminById(id,userId);
            ResponseData responseData = new ResponseData();
            responseData.getData().put("adminBean",adminBean);
            logger.info("发送findAdminById的响应:" + responseData);
            return responseData;
            }

    @ApiOperation("登录用户信息查询方法")
    @ApiImplicitParam(name = "session", value = "管理员session", dataType = "HttpSession")
    @RequestMapping("showAdminMessage")
    public ResponseData showAdminMessage(HttpSession session){
        logger.info("收到findAdminById请求：" + "session:" + session);
        Integer id = (Integer) session.getAttribute("id");
        UserBean userBean = (UserBean) session.getAttribute("user");
        AdminBean  adminBean= adminService.findAdminById(id,userBean.getId());
        ResponseData responseData = new ResponseData();
        responseData.getData().put("adminBean",adminBean);
        logger.info("发送findAdminById的响应:" + responseData);
        return responseData;
    }
}
