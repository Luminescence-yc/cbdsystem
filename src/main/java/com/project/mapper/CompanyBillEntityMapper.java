package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.CompanyBillEntity;
import com.project.entity.CountEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: CompanyBillEntityMapper
 * @Description: 企业账单 Mapper
 * @date 2019年05月31日 16:36
 */
public interface CompanyBillEntityMapper extends BaseMapper<CompanyBillEntity> {

    /**
     * 动态查询，企业账单
     * @param condition 查询条件 起始时间，结束时间
     * @param page 分页对象
     * @return 平台账单对象集合
     * @throws Exception
     */
    @Select("<script>" +
            "select id as id," +
            "         tradeDate as tradeDate," +
            "         tradeTime as tradeTime," +
            "         expend as expend, " +
            "         income as income," +
            "         remark as remark from t_companyBill" +
            " <where>" +
            "companyId = #{condition.companyId}" +
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
    List<CompanyBillEntity> findCbdBillInfoByCondition(Page<CompanyBillEntity> page,@Param("condition") Map<String, String> condition) throws Exception;

    /**
     * 统计 企业交易总次数、交易总支出、交易总收入
     * @param companyId 企业ID
     * @return 统计对象
     * @throws Exception
     */
    @Select("select count(id) as dealNum,sum(expend) as expenditure ,sum(income) as generalIncome from t_companybill where companyId = #{companyId} ")
    CountEntity countAdmin (int companyId)throws Exception;

    /**
     * 添加 企业账单
     * @param companyBillEntity 企业账单对象
     * @return 添加成功返回 1
     * @throws Exception
     */
    @Insert("insert into t_companybill values(null,#{companyId},#{tradeDate},#{tradeTime},#{expend},#{income},#{remark})")
    int addCompanyBill(CompanyBillEntity companyBillEntity)throws Exception;
}
