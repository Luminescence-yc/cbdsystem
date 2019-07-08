package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.CompanyContractEntity;
import com.project.entity.ExternalContractEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName: ExternalcontractEntityMapper
 * @Description: 外部合约Mapper
 * @date 2019年05月30日 21:45
 */
@Component
public interface ExternalcontractEntityMapper extends BaseMapper<ExternalContractEntity> {
    @Select("<script>" +
            "select * from t_externalcontract" +
            " <where>" +
            "<if test='condition.companyName!=null'>and companyName like concat('%',#{condition.companyName},'%')</if> " +
            "<if test='condition.parkingAddress!=null'>and parkingAddress like concat('%',#{condition.parkingAddress},'%')</if>" +
            "<if test='condition.externalPrice!=null'>and externalPrice like concat('%',#{condition.externalPrice},'%')</if>" +
            "</where>" +
            "</script>")
    public List<ExternalContractEntity> findByCondition(Page<ExternalContractEntity> page, @Param("condition") Map<String, String> condition);

    @Insert("insert into t_companycontract values(null,#{contractNum}," +
            "#{companyName},#{contactPerson},#{contactTel}," +
            "#{companyAddress},#{contractStartDate},#{contractEndDate}" +
            ",#{externalPrice},#{contractPicture},#{parkingAddress},#{oldContractNum})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int addExternalContract(ExternalContractEntity externalContractEntity);
}