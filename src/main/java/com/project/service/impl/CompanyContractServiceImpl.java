package com.project.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CbdParkingBean;
import com.project.bean.CompanyBean;
import com.project.bean.CompanyContractBean;
import com.project.dao.ICbdBillDao;
import com.project.dao.ICbdParkingDao;
import com.project.dao.ICompanyBillDao;
import com.project.dao.ICompanyContractDao;
import com.project.entity.CbdBillEntity;
import com.project.entity.CompanyBillEntity;
import com.project.entity.CompanyContractEntity;
import com.project.entity.CompanyEntity;
import com.project.service.ICompanyContractService;
import com.project.test.Userbean;
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
 * @ClassName:
 * @Description:
 * @date 2019年05月30日 13:33
 */
@Component
@Service
@Transactional
public class CompanyContractServiceImpl implements ICompanyContractService {
    @Autowired
    private ICompanyContractDao companyContractDao;
    @Autowired
    private ICbdParkingDao cbdParkingDao;
    @Autowired
    private ICompanyBillDao iCompanyBillDao;
    @Autowired
    private ICbdBillDao iCbdBillDao;

    @Override
    public int addCompanyContract(CompanyContractBean companyContractBean) {
        CompanyContractEntity companyContractEntity = new CompanyContractEntity();
        BeanUtils.copyProperties(companyContractBean, companyContractEntity);
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(companyContractBean.getCompanyId());
        companyContractEntity.setCompanyEntity(companyEntity);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        companyContractEntity.setContractNum("停字" + date);
        try {
            companyContractDao.add(companyContractEntity);
            List<Integer> list = companyContractBean.getCbdParkingId();
            cbdParkingDao.updateParking(list, companyContractEntity.getCompanyEntity().getId(), companyContractEntity.getId(), "租赁中");

            CompanyBillEntity companyBillEntity = new CompanyBillEntity();
            CbdBillEntity cbdBillEntity = new CbdBillEntity();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dates = df.format(new Date());

            companyBillEntity.setCompanyId(companyContractBean.getCompanyBean().getId());
            //交易开始日期
            companyBillEntity.setTradeDate(dates.split(" ")[0] );
            //交易时间
            companyBillEntity.setTradeTime(dates.split(" ")[1]);
            //企业支出
            companyBillEntity.setExpend(companyContractEntity.getCompanyPrice());
            //企业备注
            companyBillEntity.setRemark("您企业通过租户合约"+companyContractEntity.getContractNum()+"，支出了" +companyContractBean.getCompanyPrice());

            //交易开始日期
            cbdBillEntity.setTradeDate(dates.split(" ")[0] );
            //交易时间
            cbdBillEntity.setTradeTime(dates.split(" ")[1]);
            //平台收入
            cbdBillEntity.setIncome(companyContractEntity.getCompanyPrice());
            //平台备注
            cbdBillEntity.setRemark("通过租户合约"+companyContractEntity.getContractNum()+"，收入了" +companyContractBean.getCompanyPrice());
            iCompanyBillDao.addCompanyBill(companyBillEntity);
            iCbdBillDao.addAdminBill(cbdBillEntity);

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public CompanyContractBean findById(int id) {
        CompanyContractBean companyContractBean = null;
        try {
            CompanyContractEntity companyContractEntity = companyContractDao.findById(id);
            CompanyEntity companyEntity = companyContractEntity.getCompanyEntity();
            companyContractBean = new CompanyContractBean();
            CompanyBean companyBean = new CompanyBean();
            BeanUtils.copyProperties(companyContractEntity, companyContractBean);
            BeanUtils.copyProperties(companyEntity, companyBean);
            companyContractBean.setCompanyBean(companyBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companyContractBean;
    }

    @Override
    public int update(CompanyContractBean companyContractBean) {
        CompanyContractEntity companyContractEntity = new CompanyContractEntity();
        BeanUtils.copyProperties(companyContractBean, companyContractEntity);
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(companyContractBean.getCompanyBean().getId());
        companyContractEntity.setCompanyEntity(companyEntity);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        companyContractEntity.setContractNum("停" + date);
        try {
            companyContractDao.add(companyContractEntity);
            CompanyBillEntity companyBillEntity = new CompanyBillEntity();
            CbdBillEntity cbdBillEntity = new CbdBillEntity();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dates = df.format(new Date());
            companyBillEntity.setCompanyId(companyContractBean.getCompanyBean().getId());
            //交易开始日期
            companyBillEntity.setTradeDate(dates.split(" ")[0] );
            //交易时间
            companyBillEntity.setTradeTime(dates.split(" ")[1]);
            //企业支出
            companyBillEntity.setExpend(companyContractEntity.getCompanyPrice());
            //企业备注
            companyBillEntity.setRemark("您企业通过租户合约"+companyContractEntity.getContractNum()+"，支出了" +companyContractBean.getCompanyPrice());
            //交易开始日期
            cbdBillEntity.setTradeDate(dates.split(" ")[0] );
            //交易时间
            cbdBillEntity.setTradeTime(dates.split(" ")[1]);
            //平台收入
            cbdBillEntity.setIncome(companyContractEntity.getCompanyPrice());
            //平台备注
            cbdBillEntity.setRemark("通过租户合约"+companyContractEntity.getContractNum()+"，收入了" +companyContractBean.getCompanyPrice());
            iCompanyBillDao.addCompanyBill(companyBillEntity);
            iCbdBillDao.addAdminBill(cbdBillEntity);

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        try {
            cbdParkingDao.updateParkingNo(id, "未租赁");
            companyContractDao.delete(id);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Page<CompanyContractBean> findCompanyContractByCondition(Map<String, String> condition) {
        Page<CompanyContractEntity> entityPage = new Page<CompanyContractEntity>(Integer.parseInt(condition.get("page")), Integer.parseInt(condition.get("size")));
        Page<CompanyContractBean> beanPage = new Page<CompanyContractBean>();
        try {
            List<CompanyContractEntity> contractEntities = companyContractDao.findByCondition(entityPage, condition);
            entityPage.setRecords(contractEntities);
            BeanUtils.copyProperties(entityPage, beanPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }

    /**
     * 根据合同生效日期和结束日期查询
     *
     * @param page      分页
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 租户合约实体
     * @throws Exception
     */
    @Override
    public Page<CompanyContractBean> findByStartAndEnd(Page<CompanyContractEntity> page, String startDate, String endDate) throws Exception {
        Page<CompanyContractEntity> entityPage = new Page<CompanyContractEntity>();
        Page<CompanyContractBean> beanPage = new Page<CompanyContractBean>();
        try {
            List<CompanyContractEntity> contractEntities = companyContractDao.findByStartAndEnd(entityPage,startDate,endDate);
            entityPage.setRecords(contractEntities);
            BeanUtils.copyProperties(entityPage, beanPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }

    /**
     * 多条件动态查询
     *
     * @param condition 动态条件集合 开始日期和结束日期
     * @return Exception
     */
    @Override
    public Page<CompanyContractBean> findByCompanyIdAndStartAndEndDate(Map<String, String> condition) throws Exception {
        Page<CompanyContractBean> page = new Page<CompanyContractBean>(Integer.parseInt(condition.get("page")), Integer.parseInt(condition.get("size")));
        List<CompanyContractBean> companyContractBeans=companyContractDao.findByCompanyIdAndStartAndEndDate(page,condition);
        page.setRecords(companyContractBeans);
        return page;


//        Page<CompanyContractEntity> entityPage = new Page<CompanyContractEntity>(Integer.parseInt(condition.get("page")), Integer.parseInt(condition.get("size")));
//        Page<CompanyContractBean> beanPage = new Page<CompanyContractBean>();
//        try {
//            List<CompanyContractEntity> contractEntities = companyContractDao.findByCompanyIdAndStartAndEndDate(entityPage,condition);
//            entityPage.setRecords(contractEntities);
//            BeanUtils.copyProperties(entityPage, beanPage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return beanPage;
    }


}
