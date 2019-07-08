package com.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.CbdBillEntity;
import com.project.entity.CountEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: CbdBillEntityMapper
 * @Description: 平台账单 Mapper
 * @date 2019年06月01日 11:22
 */
public interface CbdBillEntityMapper extends BaseMapper<CbdBillEntity> {

    /**
     * 动态查询，平台账单
     *
     * @param page
     * @param condition 查询条件 起始时间，结束时间
     * @return 平台账单对象集合
     * @throws Exception
     */
    @Select("<script> select  id as id," +
            "         tradeDate as tradeDate," +
            "         tradeTime as tradeTime," +
            "         expand as expand, " +
            "         income as income," +
            "         remark as remark from t_cbdBill "+
            " <where> " +
            "<if test='condition.startTime!=null and condition.endTime == null'>" +
            " tradeDate  &gt;= #{condition.startTime} " +
            "</if>" +
            "<if test='condition.startTime==null and condition.endTime != null'>" +
            " tradeDate  &lt;= #{condition.endTime} " +
            "</if>" +
            "<if test='condition.startTime!=null and condition.endTime != null'>" +
            " tradeDate &gt;= #{condition.startTime} and tradeDate &lt;= #{condition.endTime} " +
            "</if>" +
            "</where></script>")
    List<CbdBillEntity> findCbdBillInfoByCondition(Page<CbdBillEntity> page, @Param("condition") Map<String, String> condition) throws Exception;

    /**
     * 统计 超级管理员交易总次数、交易总支出、交易总收入
    // * @param companyId 超级管理员ID
     * @return 统计对象
     * @throws Exception
     */
    @Select("select count(id) as dealNum,sum(expand) as expenditure ,sum(income) as generalIncome from t_cbdbill ")
    CountEntity countAdmin ()throws Exception;

    /**
     * 添加超级管理员 账单
     * @param cbdBillEntity 平台账单对象
     * @return 添加成功返回 1
     * @throws Exception
     */
    @Insert("insert into t_cbdBill values(null,#{tradeDate},#{tradeTime},#{expand},#{income},#{remark})")
    int addAdminBill(CbdBillEntity cbdBillEntity)throws Exception;
}