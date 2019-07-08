package com.project.cbdsystem;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.bean.ComplaintBean;
import com.project.entity.ComplaintEntity;
import com.project.entity.PersonalEntity;
import com.project.entity.RentHistoryEntity;
import com.project.entity.SellHistoryEntity;
import com.project.service.IComplaintService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 投诉受理业务层测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ComplaintServiceImplTest {
    @Autowired
    private IComplaintService complaintService;
    @Test
    public void findComplaintByStatus() {
        IPage<ComplaintBean> page= complaintService.findComplaintByStatus(1,5);
        System.out.println(page);
    }

    @Test
    public void findById() {
        ComplaintBean complaintBean= complaintService.findById(2);
        System.out.println(complaintBean);
    }

    @Test
    public void updateStatus() {
        complaintService.updateStatus(1,"投诉生效");
    }

    @Test
    public void addComplaint() {

    }
}