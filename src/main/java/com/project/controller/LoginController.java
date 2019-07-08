package com.project.controller;

import com.project.bean.PowerBean;
import com.project.bean.RoleBean;
import com.project.bean.UserBean;
import com.project.service.IAdminService;
import com.project.service.ICompanyService;
import com.project.service.ILogService;
import com.project.service.IPersonalService;
import com.project.util.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 万晓川
 * @ClassName:登录控制器
 * @Description:
 * @date 2019年05月30日 14:52
 */
@Api("登录页面控制器")
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private ILogService logService;
    @Autowired
    private IPersonalService personalService;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IAdminService adminService;

    @ApiOperation("登录的请求方法")
    @ApiImplicitParam(name = "userBean", value = "用户Bean", dataType = "UserBean")
    @RequestMapping("login")
    public ResponseData login(UserBean userBean, String type) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userBean.getUsername(), userBean.getPassword());
        ResponseData responseData = new ResponseData();
        try {
            subject.login(token);
            UserBean user = (UserBean) subject.getSession().getAttribute("user");
            List<String> roleList = new ArrayList<>();
            int id = 0;
            for (RoleBean roleBean : user.getRoleBeans()) {
                roleList.add(roleBean.getRole());
            }
            if (roleList.contains("personal")) {
                id = personalService.findByUserId(user.getId());
                if (type!=null){
                    responseData.setMessage("UnknownAccount");
                    return responseData;
                }
            } else if (roleList.contains("company")) {
                id = companyService.findByUserId(user.getId());
                if (type!=null){
                    responseData.setMessage("UnknownAccount");
                    return responseData;
                }
            } else {
                id = adminService.findByUserId(user.getId());
                if ("admin".equals(type)==false){
                    responseData.setMessage("UnknownAccount");
                    return responseData;
                }
            }
            responseData.getData().put("user", user);
            subject.getSession().setAttribute("id", id);
            return responseData;
        } catch (UnknownAccountException e) {
            responseData.setMessage("UnknownAccount");
            return responseData;
        } catch (IncorrectCredentialsException e) {
            responseData.setMessage("ErrorPassword");
            return responseData;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.unauthorized();
        }
    }

    @ApiOperation("注销的请求方法")
    @RequestMapping("logout")
    public ResponseData logout() {
        Subject subject = SecurityUtils.getSubject();
        ResponseData responseData = new ResponseData();
        try {
            subject.logout();
            return responseData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseData.unauthorized();
    }
}
