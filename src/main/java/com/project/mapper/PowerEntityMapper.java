package com.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.entity.PowerEntity;
import com.project.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 万晓川
 * @version v1.0
 * @ClassName PowerEntityMapper
 * @Description 权限映射器
 * @date 2019年05月31日 14:09
 */
public interface PowerEntityMapper extends BaseMapper<PowerEntity> {
    /**
     * 根据角色Id查找权限实体对象集合
     *
     * @param roleId
     * @return 权限实体对象集合
     */
    @Select("select * from t_power where roleId=#{roleId}")
    List<PowerEntity> selectByRoleId(@Param("roleId") int roleId);
}