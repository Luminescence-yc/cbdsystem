package com.project.cbdsystem;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.*;
import com.project.entity.CompanyEntity;
import com.project.entity.RoleEntity;
import com.project.entity.UserEntity;
import com.project.service.ICbdParkingService;
import com.project.service.ICompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CheckedInputStream;

/**
 * @author 刘再桦
 * @version v1.0
 * @ClassName
 * @Description
 * @date 2019年06月01日 19:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyServiceImpl {
    @Autowired
    private ICompanyService companyService;

    @Test
    public void findCompanyByCondition(){
        Map<String,String> map = new HashMap<>();
        map.put("page","1");
        map.put("size","3");
        map.put("companyName","赵");
        map.put("username","嘻");
        Page<CompanyBean> page = companyService.findCompanyByCondition(map);
        System.out.println(page);
    }

    @Test
    public void findCompanyById(){
        companyService.findCompanyById(3);
    }

    @Test
    public void addCompany(){
        List<RoleEntity> roleEntities = new ArrayList<>();
        RoleEntity roleEntity = new RoleEntity();
        CompanyEntity companyEntity = new CompanyEntity();
        UserEntity userEntity = new UserEntity();
        companyEntity.setTel("13909058144");
        companyEntity.setCompanyName("赵舒翘");
        companyEntity.setFloor("weinsdi");
        companyEntity.setContactPerson("shujushi");
        userEntity.setPassword("123");
        userEntity.setUsername("嘻嘻嘻");
        userEntity.setSalt("sss");

        roleEntity.setId(1);
        roleEntities.add(roleEntity);
        userEntity.setRoleEntities(roleEntities);
        companyEntity.setUserEntity(userEntity);

        CompanyBean companyBean = new CompanyBean();
        companyBean.setTel(companyEntity.getTel());
        companyBean.setFloor(companyEntity.getFloor());
        companyBean.setContactPerson(companyEntity.getContactPerson());
        companyBean.setCompanyName(companyEntity.getCompanyName());
        companyBean.setUsername(companyEntity.getUserEntity().getUsername());
        companyBean.setPassword(companyEntity.getUserEntity().getPassword());
        companyService.addCompany(companyBean);
    }

    @Test
    public void updateCompany(){
        CompanyBean companyBean = new CompanyBean();
        companyBean.setTel("144547744");
        companyBean.setId(41);
        companyBean.setUserId(22);
        companyBean.setContactPerson("马云");
        companyBean.setUsername("wxc");
        companyBean.setPassword("321");
        companyService.updateCompany(companyBean);
    }

    @Test
    public void deleteCompany(){
        companyService.deleteCompany(15);
    }
}
