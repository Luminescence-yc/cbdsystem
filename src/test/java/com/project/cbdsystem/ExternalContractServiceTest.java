package com.project.cbdsystem;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CbdParkingBean;
import com.project.bean.ExternalContractBean;
import com.project.dao.IExternalContractDao;
import com.project.entity.ExternalContractEntity;
import com.project.service.ICbdParkingService;
import com.project.service.IExternalContractService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName:
 * @Description:
 * @date 2019年06月03日 11:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExternalContractServiceTest {
    @Autowired
    private IExternalContractService contractService;

    @Test
    public void add() {
        ExternalContractBean contractBean = contractService.findById(3);
        CbdParkingBean cbdParkingBean = new CbdParkingBean();
        CbdParkingBean cbdParkingBean1 = new CbdParkingBean();
        cbdParkingBean.setAddress("武侯区");
        cbdParkingBean.setAreaNum("CF");
        cbdParkingBean.setParkingNum("12");
        cbdParkingBean.setCount(5);
        cbdParkingBean1.setAddress("金牛区");
        cbdParkingBean1.setAreaNum("CD");
        cbdParkingBean1.setParkingNum("1");
        cbdParkingBean1.setCount(5);
        List<CbdParkingBean> parkingBeans = new ArrayList<>();
        parkingBeans.add(cbdParkingBean);
        parkingBeans.add(cbdParkingBean1);
        contractBean.setCbdParkingBeans(parkingBeans);
        contractService.addExternalContract(contractBean);
    }

    @Test
    public void find() {
        ExternalContractBean contractBean = contractService.findById(3);
        System.out.println(contractBean);
    }

    @Test
    public void delete() {
        Map<String, String> condition = new HashMap<>();
        condition.put("page", "1");
        condition.put("size", "10");
        condition.put("companyName", null);
        condition.put("address", null);
        condition.put("companyPrice", null);

        Page<ExternalContractBean> contractEntities = contractService.findByCondition(condition);
        System.out.println(contractEntities);

    }

    @Test
    public void findByCondition() {
        Map<String, String> condition = new HashMap<>();
        condition.put("companyName", null);
        condition.put("address", null);
        condition.put("companyPrice", null);
        condition.put("page", "2");
        condition.put("size", "3");
        Page<ExternalContractBean> page = null;
        try {
            page = contractService.findByCondition(condition);
            System.out.println(page.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {


    }


}
