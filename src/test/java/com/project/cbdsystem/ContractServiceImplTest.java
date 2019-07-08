package com.project.cbdsystem;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.bean.ContractBean;
import com.project.service.IContractService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘晶晶
 * @ClassName:
 * @Description:
 * @date 2019年06月01日 10:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContractServiceImplTest {
    @Autowired
    private IContractService iContractService;
    @Test
    public void findContractByCondition() {
        Map<String,String> map=new HashMap<>();
        map.put("page", "1");
        map.put("size", "18");
        map.put("startTime","2019-01-01");
        map.put("endTime","2019-01-02");
        IPage<ContractBean> page = iContractService.findContractByCondition(map);
        System.out.println(page);
    }

    @Test
    public void updateStatus() {
        int i = iContractService.updateStatus(1, "1");
        System.out.println(i);
    }

    @Test
    public void findNotAcceptContract() {
        IPage<ContractBean> page = iContractService.findNotAcceptContract(1, 18);
        System.out.println(page);
    }

    @Test
    public void findContractBySellId() {
        ContractBean contractBySellId = iContractService.findContractBySellId(1);
        Assert.assertNotNull(contractBySellId);
    }
}