package com.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.ContractEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 刘晶晶
 * @version v1.0
 * @ClassName: ContractEntityMapper
 * @Description: 合同Mapper
 * @date 2019年05月31日 9:38
 */
public interface ContractEntityMapper extends BaseMapper<ContractEntity> {
    /**
     * 添加合同
     *
     * @param contractEntity 合同实体类
     * @return
     */
    @Insert("insert into t_contract values (null,#{contractEntity.sellParkingEntity.id}," +
            "#{contractEntity.buyer.id},#{contractEntity.seller.id}," +
            "CONCAT('停字',YEAR(NOW()),MONTH(NOW()),DAY(NOW())),CURDATE(),null ,-1,-1,0)")
    int addContract(@Param("contractEntity") ContractEntity contractEntity);

    /**
     * 根据合同Id查询交易成功的车位Id
     *
     * @param id 合同Id
     * @return 合同Id
     * @throws Exception
     */
    @Select("select count(*) from t_contract where id=#{id} and sellerAgree=1 and buyerAgree=1")
    int getContractByBuyerAgreeAndSellerAgree(@Param("id") int id);

    /**
     * 根据合同Id修改卖家意见
     *
     * @param id          合同Id
     * @param sellerAgree 卖家意见，0拒绝，1同意
     * @return 返回非0为成功，0失败
     * @throws Exception
     */
    @Update("update t_contract set sellerAgree=#{sellerAgree} where id=#{id}")
    int updateSeller(@Param("id") int id, @Param("sellerAgree") int sellerAgree);

    /**
     * 根据合同Id修改买家意见
     *
     * @param id         合同Id
     * @param buyerAgree 买家意见，0拒绝，1同意
     * @return 返回非0为成功，0失败
     * @throws Exception
     */
    @Update("update t_contract set buyerAgree=#{buyerAgree} where id=#{id}")
    int updateBuyer(@Param("id") int id, @Param("buyerAgree") int buyerAgree);

    /**
     * 动态条件查询未受理的合同
     *
     * @param page      分页对象
     * @param condition 条件
     * @return 合同对象集合
     */
    @Select({"<script> SELECT * FROM t_contract " +
            "<where>" +
            "status=0" +
            "<if test='condition.startTime!=null'> and applyDate &gt;= #{condition.startTime} </if>" +
            "<if test='condition.endTime!=null'> and applyDate &lt;= #{condition.endTime} </if>" +
            "<if test='condition.contractNum!=null'> and contractNum =#{condition.contractNum} </if>" +
            "</where ></script>"})
    @Results({
            @Result(column = "sellId", property = "sellParkingEntity", one = @One(select = "com.project.mapper.SellParkingEntityMapper.selectById")),
            @Result(column = "buyPersonalId", property = "buyer", one = @One(select = "com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll")),
            @Result(column = "sellPersonalId", property = "seller", one = @One(select = "com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll"))
    })
    List<ContractEntity> findByCondition(Page<ContractEntity> page, @Param("condition") Map<String, String> condition);

    /**
     * 修改合同状态
     *
     * @param id     合同主键
     * @param status 合同状态
     * @return
     */
    @Update("update t_contract set status=#{status} where id=#{id}")
    int updateStatus(@Param("id") int id, @Param("status") String status);

    /**
     * 查询未受理的合同
     *
     * @param page 分页对象
     * @return 合同对象集合
     */
    @Select("select * from t_contract where status=0 and buyerAgree=0")
    @Results({
            @Result(column = "sellId", property = "sellParkingEntity", one = @One(select = "com.project.mapper.SellParkingEntityMapper.selectById")),
            @Result(column = "buyPersonalId", property = "buyer", one = @One(select = "com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll")),
            @Result(column = "sellPersonalId", property = "seller", one = @One(select = "com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll"))
    })
    List<ContractEntity> findNoAccept(Page<ContractEntity> page);

    /**
     * 根据出售车位ID查询合同
     *
     * @param sellId 出售车位ID
     * @return 合同对象
     */
    @Select("select * from  t_contract where sellId=#{sellId}")
    @Results({
            @Result(column = "sellId", property = "sellParkingEntity", one = @One(select = "com.project.mapper.SellParkingEntityMapper.selectById")),
            @Result(column = "buyPersonalId", property = "buyer", one = @One(select = "com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll")),
            @Result(column = "sellPersonalId", property = "seller", one = @One(select = "com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll"))
    })
    ContractEntity findContractById(int sellId);
}