package com.project.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CbdParkingBean;
import com.project.bean.ExternalContractBean;
import com.project.dao.ICbdBillDao;
import com.project.dao.ICbdParkingDao;
import com.project.dao.IExternalContractDao;
import com.project.entity.CbdBillEntity;
import com.project.entity.CbdParkingEntity;
import com.project.entity.ExternalContractEntity;
import com.project.service.IExternalContractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName:ExternalContractServiceImpl
 * @Description:
 * @date 2019年05月30日 13:27
 */
@Service
@Component
@Transactional
public class ExternalContractServiceImpl implements IExternalContractService {
    @Autowired
    private IExternalContractDao externalContractDao;
    @Autowired
    private ICbdParkingDao cbdParkingDao;
    @Autowired
    private ICbdBillDao iCbdBillDao;

    @Override
    public int addExternalContract(ExternalContractBean externalContractBean) {
        ExternalContractEntity externalContractEntity = new ExternalContractEntity();
        List<CbdParkingBean> cbdParkingBeans = externalContractBean.getCbdParkingBeans();
        try {
            BeanUtils.copyProperties(externalContractBean, externalContractEntity);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String date = sdf.format(new Date());
            externalContractEntity.setContractNum("停" + date);
            externalContractDao.addExternalContract(externalContractEntity);

            CbdBillEntity cbdBillEntity = new CbdBillEntity();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dates = df.format(new Date());
            //平台支出
            cbdBillEntity.setExpand(externalContractBean.getExternalPrice());
            //平台交易开始日期
            cbdBillEntity.setTradeDate(dates.split(" ")[0] );
            //平台交易时间
            cbdBillEntity.setTradeTime(dates.split(" ")[1]);
            //平台备注
            cbdBillEntity.setRemark("通过外部合约,支出了" + cbdBillEntity.getExpand());
            iCbdBillDao.addAdminBill(cbdBillEntity);

            for (CbdParkingBean cbdParking : cbdParkingBeans) {
                CbdParkingEntity cbdParkingEntity = new CbdParkingEntity();
                List<Integer> parkingNumList = new ArrayList<>();
                BeanUtils.copyProperties(cbdParking, cbdParkingEntity);
                cbdParkingEntity.setExternalcontractEntity(externalContractEntity);
                cbdParkingEntity.setStatus("未租赁");
                cbdParkingEntity.setFinalDate(externalContractEntity.getContractEndDate());
                int parkingNum = Integer.parseInt(cbdParking.getParkingNum());
                for (int i = 0; i < cbdParking.getCount(); i++) {
                    parkingNumList.add(parkingNum);
                    parkingNum++;
                }
                cbdParkingDao.addParking(cbdParkingEntity, parkingNumList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ExternalContractBean findById(int id) {
        ExternalContractEntity contractEntity = null;
        ExternalContractBean contractBean = null;
        try {
            contractEntity = externalContractDao.findById(id);
            if (contractEntity != null) {
                contractBean = new ExternalContractBean();
                BeanUtils.copyProperties(contractEntity, contractBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contractBean;
    }

    @Override
    public int delete(int id) {
        try {
            if (cbdParkingDao.findNoRentCbdParkingByExternalcontractId(id, "租赁中") == 0) {
                cbdParkingDao.delete(id);
                externalContractDao.delete(id);
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Page<ExternalContractBean> findByCondition(Map<String, String> condition) {
        Page<ExternalContractEntity> page = new Page<>(Integer.parseInt(condition.get("page")), Integer.parseInt(condition.get("size")));
        Page<ExternalContractBean> beanPage = new Page<>();
        Page<ExternalContractEntity> entityPage = null;
        try {
            entityPage = page.setRecords(externalContractDao.findByCondition(page, condition));
            BeanUtils.copyProperties(entityPage, beanPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }

    @Override
    public int update(ExternalContractBean contractBean) {
        ExternalContractEntity externalContractEntity = new ExternalContractEntity();
        BeanUtils.copyProperties(contractBean, externalContractEntity);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        externalContractEntity.setContractNum("停" + date);
        try {
            externalContractDao.addExternalContract(externalContractEntity);
            cbdParkingDao.updateParkingFinalDate(externalContractEntity.getId(), externalContractEntity.getContractEndDate());

            CbdBillEntity cbdBillEntity = new CbdBillEntity();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dates = df.format(new Date());
            //平台支出
            cbdBillEntity.setExpand(contractBean.getExternalPrice());
            //平台交易开始日期
            cbdBillEntity.setTradeDate(dates.split(" ")[0] );
            //平台交易时间
            cbdBillEntity.setTradeTime(dates.split(" ")[1]);
            //平台备注
            cbdBillEntity.setRemark("通过外部合约,支出了" + cbdBillEntity.getExpand());
            iCbdBillDao.addAdminBill(cbdBillEntity);

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
