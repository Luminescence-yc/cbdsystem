package com.project.cbdsystem;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.dao.IContractDao;
import com.project.entity.ContractEntity;
import com.project.entity.PersonalEntity;
import com.project.entity.SellParkingEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘晶晶
 * @version v1.0
 * @ClassName: ContractDaoImplTest
 * @Description:
 * @date 2019年05月31日 16:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContractDaoImplTest {

    @Autowired
    private IContractDao iContractDao;
    @Test
    public void addContract() {
        ContractEntity contractEntity=new ContractEntity();
        PersonalEntity personalEntity = new PersonalEntity();
        PersonalEntity personalEntity1 = new PersonalEntity();
        SellParkingEntity sellParkingEntity=new SellParkingEntity();
        personalEntity.setId(10);
        personalEntity1.setId(12);
        sellParkingEntity.setId(6);

        contractEntity.setBuyer(personalEntity);
        contractEntity.setSeller(personalEntity1);
        contractEntity.setSellParkingEntity(sellParkingEntity);
        try {
            int i = iContractDao.addContract(contractEntity);
            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getContractByCondition() {
        Map<String,String> map=new HashMap<>();
        map.put("startTime","2019-01-01");
        map.put("endTime","2019-01-02");
        Page<ContractEntity> page = new Page<ContractEntity>(1,10);
        try {
            Page<ContractEntity> page1=page.setRecords(iContractDao.getContractByCondition(page, map));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateStatus() {
        try {
            int i = iContractDao.updateStatus(21, "1");
            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getNotAcceptContract() {

    }

    @Test
    public void getContractBySellId() {
        try {
            ContractEntity contractEntity = iContractDao.getContractBySellId(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}