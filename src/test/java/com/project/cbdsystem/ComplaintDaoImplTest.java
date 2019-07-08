package com.project.cbdsystem;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.ResponseTimeBean;
import com.project.dao.IComplaintDao;
import com.project.entity.ComplaintEntity;
import com.project.entity.PersonalEntity;
import com.project.entity.ResponseTimeEntity;
import com.project.entity.SellHistoryEntity;
import com.project.mapper.PersonalEntityMapper;
import com.project.mapper.SellHistoryEntityMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ComplaintDaoImplTest {
    @Autowired
    private IComplaintDao complaintDao;
    @Autowired
    private SellHistoryEntityMapper sellHistoryEntityMapper;
    @Autowired
    private PersonalEntityMapper personalEntityMapper;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findComplaintByStatus() {
        Page<ComplaintEntity> page=new Page<ComplaintEntity>(1,5);
        List<ComplaintEntity> list=complaintDao.findComplaintByStatus(page);
        for (ComplaintEntity complaintEntity : list) {
            System.out.println(complaintEntity);
        }

    }

    @Test
    public void findById() {
        ComplaintEntity complaintEntity=complaintDao.findById(1);
        System.out.println(complaintEntity);
    }

    @Test
    public void updateStatus() {
      //  complaintDao.updateStatus(1,"投诉生效");

        try {
         SellHistoryEntity sellHistoryEntity=   sellHistoryEntityMapper.findSellByPersonalId(1);
           // PersonalEntity personalEntity=personalEntityMapper.getPersonalAndUserByAll(1);
            System.out.println(sellHistoryEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}