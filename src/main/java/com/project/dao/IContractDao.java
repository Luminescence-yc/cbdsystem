package com.project.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.ContractEntity;

import java.util.List;
import java.util.Map;

/**
 * @author 刘晶晶
 * @version v1.0
 * @ClassName: IContractDao
 * @Description: 合同持久层接口
 * @date 2019年05月31日 9:16
 */

public interface IContractDao {
    /**
     * 添加合同（前台调用的），买家和卖家意见默认为-1
     *
     * @param contractEntity 合同实体类
     * @return 返回非0为成功，0失败
     * @throws Exception
     */
    int addContract(ContractEntity contractEntity) throws Exception;

    /**
     * 根据合同Id修改买家意见
     *
     * @param id         合同Id
     * @param buyerAgree 买家意见，0拒绝，1同意
     * @return 返回非0为成功，0失败
     * @throws Exception
     */
    int updateBuyer(int id, int buyerAgree) throws Exception;

    /**
     * 根据合同Id修改卖家意见
     *
     * @param id          合同Id
     * @param sellerAgree 卖家意见，0拒绝，1同意
     * @return 返回非0为成功，0失败
     * @throws Exception
     */
    int updateSeller(int id, int sellerAgree) throws Exception;

    /**
     * 根据合同Id查询交易成功的合同
     *
     * @param id 合同Id
     * @return 合同Id
     * @throws Exception
     */
    int getContractByBuyerAgreeAndSellerAgree(int id) throws Exception;

    /**
     * 动态查询得到合同集合
     *
     * @param page      分页对象
     * @param condition 查询条件
     * @return 合同集合
     * @throws Exception
     */
    List<ContractEntity> getContractByCondition(Page<ContractEntity> page, Map<String, String> condition) throws Exception;

    /**
     * 通过ID修改合同状态
     *
     * @param id     合同主键ID
     * @param status 合同状态（分为受理和未受理）
     * @return 返回非0位成功，0失败
     * @throws Exception
     */
    int updateStatus(int id, String status) throws Exception;

    /**
     * 查询未受理的合同
     *
     * @param page 分页对象
     * @return 合同对象集合
     * @throws Exception
     */
    List<ContractEntity> getNotAcceptContract(Page<ContractEntity> page) throws Exception;

    /**
     * 通过出售车位ID查询合同（前台调用）
     *
     * @param sellId 出售车位ID
     * @return 合同对象
     * @throws Exception
     */
    ContractEntity getContractBySellId(int sellId) throws Exception;

    /**
     * 通过合同id查询合同对象
     *
     * @param id 合同id
     * @return 合同对象
     * 陈云龙添加
     */
    public ContractEntity selectContractById(int id);

    /**
     * 根据合同id删除合同
     *
     * @param id 合同id
     * @return 返回非0位成功，0失败
     * 陈云龙添加
     */
    public int deleteContractById(int id);
}
