package com.project.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.ContractBean;
import com.project.bean.PersonalBean;
import com.project.bean.PersonalParkingBean;
import com.project.bean.SellParkingBean;
import com.project.dao.*;
import com.project.entity.*;
import com.project.service.IContractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 刘晶晶
 * @version v1.0
 * @ClassName: ContractServiceImpl
 * @Description: 合同业务层实现类
 * @date 2019年05月31日 17:13
 */
@Service
public class ContractServiceImpl implements IContractService {

    @Autowired
    private IContractDao iContractDao;
    @Autowired
    private ISellHistoryDao iSellHistoryDao;
    @Autowired
    private IPersonalBillDao iPersonalBillDao;
    @Autowired
    private ISellMessageDao iSellMessageDao;
    @Autowired
    private ISellParkingDao iSellParkingDao;
    @Autowired
    private IPersonalParkingDao iPersonalParkingDao;

    @Override
    public int addContract(ContractBean contractBean) {
        if (contractBean.getBuyPersonalId() == 0 || contractBean.getSellPersonalId() == 0 || contractBean.getSellId() == 0) {
            return 0;
        }
        ContractEntity contractEntity = new ContractEntity();
        PersonalEntity buyer = new PersonalEntity();
        PersonalEntity seller = new PersonalEntity();
        SellParkingEntity sellParkingEntity = new SellParkingEntity();
        buyer.setId(contractBean.getBuyPersonalId());
        seller.setId(contractBean.getSellPersonalId());
        sellParkingEntity.setId(contractBean.getSellId());
        contractEntity.setBuyer(buyer);
        contractEntity.setSeller(seller);
        contractEntity.setSellParkingEntity(sellParkingEntity);
        try {
            int i = iContractDao.addContract(contractEntity);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int updateBuyer(ContractBean contractBean) {
        try {
            int id = contractBean.getId();
            int buyerAgree = contractBean.getBuyerAgree();
            iContractDao.updateBuyer(id, buyerAgree);
            int sellId = iContractDao.getContractByBuyerAgreeAndSellerAgree(id);
            if(buyerAgree==0){
                iContractDao.deleteContractById(id);
                iSellMessageDao.deleteSellMessageByPersonIdAndSellId(contractBean.getSellId(),contractBean.getBuyPersonalId());
                iSellParkingDao.updateSellStatusById(contractBean.getSellId(),"出售中");
            }
            else if (sellId > 0) {
                ContractEntity contractEntity = iContractDao.getContractBySellId(contractBean.getSellId());
                SellHistoryEntity sellHistoryEntity = new SellHistoryEntity();
                PersonalEntity buyer = new PersonalEntity();
                PersonalEntity seller = new PersonalEntity();
                PersonalBillEntity buyerBill = new PersonalBillEntity();
                PersonalBillEntity sellerBill = new PersonalBillEntity();
                SellParkingEntity sellParking = contractEntity.getSellParkingEntity();
                PersonalParkingEntity personalParking = sellParking.getPersonalParkingEntity();
                buyer.setId(contractEntity.getBuyer().getId());
                seller.setId(contractEntity.getSeller().getId());
                sellHistoryEntity.setBuyPersonal(buyer);
                sellHistoryEntity.setSellPersonal(seller);
                sellHistoryEntity.setExternalPrice(sellParking.getSellPrice()+"");
                sellHistoryEntity.setParkingMessage(personalParking.getAddress()+personalParking.getAreaNum()+personalParking.getParkingNum());
                sellHistoryEntity.setSellDate(new Timestamp(System.currentTimeMillis())+"");
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = df.format(new Date());
                buyerBill.setExpend(sellParking.getSellPrice());
                buyerBill.setPersonalId(contractEntity.getBuyer().getId());
                buyerBill.setRemark("您购买车位"+personalParking.getAddress()+personalParking.getAreaNum()+personalParking.getParkingNum()+"，支出了"+sellParking.getSellPrice());
                buyerBill.setDealDate(date.split(" ")[0]);
                buyerBill.setDealTime(date.split(" ")[1]);
                sellerBill.setIncome(sellParking.getSellPrice());
                sellerBill.setPersonalId(contractEntity.getSeller().getId());
                sellerBill.setRemark("您卖出车位"+personalParking.getAddress()+personalParking.getAreaNum()+personalParking.getParkingNum()+"，收入了"+sellParking.getSellPrice());
                sellerBill.setDealDate(date.split(" ")[0]);
                sellerBill.setDealTime(date.split(" ")[1]);
                iSellHistoryDao.addSellHistory(sellHistoryEntity);
                iPersonalBillDao.addUserBill(buyerBill);
                iPersonalBillDao.addUserBill(sellerBill);
                iSellMessageDao.deleteSellMessageBySellId(contractBean.getSellId());
                iSellParkingDao.deleteSellParkingById(contractBean.getSellId());
                iContractDao.deleteContractById(id);
                iPersonalParkingDao.updatePersonalParkingPersonalIdAndStatusByPersonalId(seller.getId(),buyer.getId(),"未发布");
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateSeller(ContractBean contractBean) {
        try {
            int id = contractBean.getId();
            int sellerAgree = contractBean.getSellerAgree();
            iContractDao.updateSeller(id, sellerAgree);
            int sellId = iContractDao.getContractByBuyerAgreeAndSellerAgree(id);
            if(sellerAgree==0){
                iContractDao.deleteContractById(id);
                iSellMessageDao.deleteSellMessageByPersonIdAndSellId(contractBean.getSellId(),contractBean.getBuyPersonalId());
                iSellParkingDao.updateSellStatusById(contractBean.getSellId(),"出售中");
            }
            else if (sellId > 0) {
                ContractEntity contractEntity = iContractDao.getContractBySellId(contractBean.getSellId());
                SellHistoryEntity sellHistoryEntity = new SellHistoryEntity();
                PersonalEntity buyer = new PersonalEntity();
                PersonalEntity seller = new PersonalEntity();
                PersonalBillEntity buyerBill = new PersonalBillEntity();
                PersonalBillEntity sellerBill = new PersonalBillEntity();
                SellParkingEntity sellParking = contractEntity.getSellParkingEntity();
                PersonalParkingEntity personalParking = sellParking.getPersonalParkingEntity();
                buyer.setId(contractEntity.getBuyer().getId());
                seller.setId(contractEntity.getSeller().getId());
                sellHistoryEntity.setBuyPersonal(buyer);
                sellHistoryEntity.setSellPersonal(seller);
                sellHistoryEntity.setExternalPrice(sellParking.getSellPrice()+"");
                sellHistoryEntity.setParkingMessage(personalParking.getAddress()+personalParking.getAreaNum()+personalParking.getParkingNum());
                sellHistoryEntity.setSellDate(new Timestamp(System.currentTimeMillis())+"");
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = df.format(new Date());
                buyerBill.setExpend(sellParking.getSellPrice());
                buyerBill.setPersonalId(contractEntity.getBuyer().getId());
                buyerBill.setRemark("您购买车位"+personalParking.getAddress()+personalParking.getAreaNum()+personalParking.getParkingNum()+"，支出了"+sellParking.getSellPrice());
                buyerBill.setDealDate(date.split(" ")[0]);
                buyerBill.setDealTime(date.split(" ")[1]);
                sellerBill.setIncome(sellParking.getSellPrice());
                sellerBill.setPersonalId(contractEntity.getSeller().getId());
                sellerBill.setRemark("您卖出车位"+personalParking.getAddress()+personalParking.getAreaNum()+personalParking.getParkingNum()+"，收入了"+sellParking.getSellPrice());
                sellerBill.setDealDate(date.split(" ")[0]);
                sellerBill.setDealTime(date.split(" ")[1]);
                iSellHistoryDao.addSellHistory(sellHistoryEntity);
                iPersonalBillDao.addUserBill(buyerBill);
                iPersonalBillDao.addUserBill(sellerBill);
                iSellMessageDao.deleteSellMessageBySellId(contractBean.getSellId());
                iSellParkingDao.deleteSellParkingById(contractBean.getSellId());
                iContractDao.deleteContractById(id);
                iPersonalParkingDao.updatePersonalParkingPersonalIdAndStatusByPersonalId(seller.getId(),buyer.getId(),"未发布");
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public IPage<ContractBean> findContractByCondition(Map<String, String> condition) {
        Page<ContractEntity> page = new Page<ContractEntity>(Integer.parseInt(condition.get("page")), Integer.parseInt(condition.get("size")));
        Page<ContractBean> beanPage = new Page<ContractBean>();
        try {
            Page<ContractEntity> entityPage = page.setRecords(iContractDao.getContractByCondition(page, condition));
            List<ContractEntity> records = entityPage.getRecords();
            List<ContractBean> list = new ArrayList<>();
            BeanUtils.copyProperties(entityPage, beanPage);
            for (int i = 0; i < records.size(); i++) {
                ContractBean contractBean = new ContractBean();
                SellParkingBean sellParkingBean = new SellParkingBean();
                PersonalParkingBean personalParkingBean = new PersonalParkingBean();
                ContractEntity contractEntity = records.get(i);
                SellParkingEntity sellParkingEntity = contractEntity.getSellParkingEntity();
                BeanUtils.copyProperties(sellParkingEntity.getPersonalParkingEntity(), personalParkingBean);
                sellParkingBean.setParkingBean(personalParkingBean);
                BeanUtils.copyProperties(sellParkingEntity, sellParkingBean);
                contractBean.setSellParkingBean(sellParkingBean);
                contractBean.setBuyer(new PersonalBean());
                contractBean.setSeller(new PersonalBean());
                BeanUtils.copyProperties(records.get(i), contractBean);
                BeanUtils.copyProperties(records.get(i).getSellParkingEntity(), contractBean.getSellParkingBean());
                BeanUtils.copyProperties(records.get(i).getBuyer(), contractBean.getBuyer());
                BeanUtils.copyProperties(records.get(i).getSeller(), contractBean.getSeller());
                list.add(contractBean);
            }
            beanPage.setRecords(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }

    @Override
    public int updateStatus(int id, String status) {
        try {
            int i = iContractDao.updateStatus(id, status);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public IPage<ContractBean> findNotAcceptContract(int page, int size) {
        Page<ContractEntity> pageinfo = new Page<ContractEntity>(page, size);
        Page<ContractBean> beanPage = new Page<ContractBean>();
        try {
            Page<ContractEntity> pageEntity = pageinfo.setRecords(iContractDao.getNotAcceptContract(pageinfo));
            List<ContractEntity> records = pageEntity.getRecords();
            List<ContractBean> list = new ArrayList<>();
            BeanUtils.copyProperties(pageEntity, beanPage);
            for (int i = 0; i < records.size(); i++) {
                ContractBean contractBean = new ContractBean();
                SellParkingBean sellParkingBean = new SellParkingBean();
                PersonalParkingBean personalParkingBean = new PersonalParkingBean();
                ContractEntity contractEntity = records.get(i);
                SellParkingEntity sellParkingEntity = contractEntity.getSellParkingEntity();
                BeanUtils.copyProperties(sellParkingEntity.getPersonalParkingEntity(), personalParkingBean);
                sellParkingBean.setParkingBean(personalParkingBean);
                BeanUtils.copyProperties(sellParkingEntity, sellParkingBean);
                contractBean.setSellParkingBean(sellParkingBean);
                contractBean.setBuyer(new PersonalBean());
                contractBean.setSeller(new PersonalBean());
                BeanUtils.copyProperties(records.get(i), contractBean);
                BeanUtils.copyProperties(records.get(i).getSellParkingEntity(), contractBean.getSellParkingBean());
                BeanUtils.copyProperties(records.get(i).getBuyer(), contractBean.getBuyer());
                BeanUtils.copyProperties(records.get(i).getSeller(), contractBean.getSeller());
                list.add(contractBean);
            }
            beanPage.setRecords(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }

    @Override
    public ContractBean findContractBySellId(int sellId) {
        ContractBean contractBean = null;
        try {
            ContractEntity contractEntity = iContractDao.getContractBySellId(sellId);
            if (contractEntity != null) {
                contractBean = new ContractBean();
                SellParkingBean sellParkingBean = new SellParkingBean();
                PersonalParkingBean personalParkingBean = new PersonalParkingBean();
                SellParkingEntity sellParkingEntity = contractEntity.getSellParkingEntity();
                BeanUtils.copyProperties(sellParkingEntity.getPersonalParkingEntity(), personalParkingBean);
                sellParkingBean.setParkingBean(personalParkingBean);
                BeanUtils.copyProperties(sellParkingEntity, sellParkingBean);
                contractBean.setSellParkingBean(sellParkingBean);
                contractBean.setBuyer(new PersonalBean());
                contractBean.setSeller(new PersonalBean());
                BeanUtils.copyProperties(contractEntity, contractBean);
                BeanUtils.copyProperties(contractEntity.getSellParkingEntity(), contractBean.getSellParkingBean());
                BeanUtils.copyProperties(contractEntity.getBuyer(), contractBean.getBuyer());
                BeanUtils.copyProperties(contractEntity.getSeller(), contractBean.getSeller());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contractBean;
    }

    @Override
    public ContractEntity selectContractById(int id) {
        return iContractDao.selectContractById(id);
    }

    @Override
    public int deleteContractById(int id) {
        return iContractDao.deleteContractById(id);
    }
}