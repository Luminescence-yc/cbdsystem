package com.project.cbdsystem;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CompanyBean;
import com.project.bean.CompanyContractBean;
import com.project.entity.CompanyContractEntity;
import com.project.service.ICompanyContractService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author yangcheng
 * @ClassName:
 * @Description:
 * @date 2019年06月02日 13:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyContractServiceImplTest {
    @Autowired
    private ICompanyContractService iCompanyContractService;

    @Test
    public void addCompanyContract() {
        CompanyContractBean companyContractBean = new CompanyContractBean();
        companyContractBean.setCompanyPrice(10000);
        companyContractBean.setStartDate("2014-10-01");
        companyContractBean.setEndDate("2019-10-01");
        companyContractBean.setContractPicture("2.jpg");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        companyContractBean.setCbdParkingId(list);
        CompanyBean companyBean = new CompanyBean();
        companyBean.setId(3);
        companyContractBean.setCompanyBean(companyBean);
        iCompanyContractService.addCompanyContract(companyContractBean);
    }

    @Test
    public void findById() {
        System.out.println(iCompanyContractService.findById(1));
    }

    @Test
    public void update() {
        CompanyContractBean companyContractBean = new CompanyContractBean();
        companyContractBean.setCompanyPrice(12000);
        companyContractBean.setStartDate("2015-10-01");
        companyContractBean.setEndDate("2019-12-01");
        companyContractBean.setContractPicture("3.jpg");
        companyContractBean.setOldContractNum("停20190529");
        CompanyBean companyBean = new CompanyBean();
        companyBean.setId(3);
        companyContractBean.setCompanyBean(companyBean);
        iCompanyContractService.update(companyContractBean);
    }

    @Test
    public void delete() {
        iCompanyContractService.delete(30);
    }

    @Test
    public void findCompanyContractByCondition() {
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("size", "3");
        map.put("username","s");
        map.put("address","3");
        Page<CompanyContractBean> page = iCompanyContractService.findCompanyContractByCondition(map);
        System.out.println(page.getRecords());
    }
}