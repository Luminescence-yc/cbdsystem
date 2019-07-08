package com.project.dao.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.dao.ICbdBillDao;
import com.project.entity.CbdBillEntity;
import com.project.entity.CountEntity;
import com.project.mapper.CbdBillEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: ICbdBillDaoImpl
 * @Description: 平台账单 持久实现类
 * @date 2019年05月31日 17:17
 */
@ClassType(describe = "平台账单")
@Component
public class CbdBillDaoImpl implements ICbdBillDao {

    @Autowired
    private CbdBillEntityMapper cbdbillEntityMapper;

    @Override
    public List<CbdBillEntity> findCbdBillInfoByCondition(Page<CbdBillEntity> page,Map<String, String> condition) throws Exception {
        return cbdbillEntityMapper.findCbdBillInfoByCondition(page,condition);

    }

    @Override
    public int addAdminBill(CbdBillEntity cbdBillEntity) throws Exception {
        return cbdbillEntityMapper.addAdminBill(cbdBillEntity);
    }

    @Override
    public CountEntity countAdmin() throws Exception {
        return cbdbillEntityMapper.countAdmin();
    }
}
