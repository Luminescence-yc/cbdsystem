package com.project.service.impl;

import com.project.bean.PowerBean;
import com.project.bean.RoleBean;
import com.project.bean.UserBean;
import com.project.dao.IUserDao;
import com.project.entity.PowerEntity;
import com.project.entity.RoleEntity;
import com.project.entity.UserEntity;
import com.project.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 万晓川
 * @version v1.0
 * @ClassName IUserService
 * @Description 用户业务接口
 * @date 2019年05月30日 14:28
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao iUserDao;

    @Override
    public UserBean findByUsername(String username) {
        UserEntity userEntity = iUserDao.getByUsername(username);
        UserBean userBean = null;
        List<RoleBean> roleBeans = new ArrayList<>();
        try {
            if (userEntity != null) {
                userBean = new UserBean();
                BeanUtils.copyProperties(userEntity, userBean);
                List<RoleEntity> roleEntities = userEntity.getRoleEntities();
                for (int i = 0; i < roleEntities.size(); i++) {
                    RoleBean roleBean = new RoleBean();
                    List<PowerBean> powerBeans = new ArrayList<>();
                    List<PowerEntity> powerEntities = roleEntities.get(i).getPowerEntities();
                    BeanUtils.copyProperties(roleEntities.get(i), roleBean);
                    for (int j = 0; j < powerEntities.size(); j++) {
                        PowerBean powerBean = new PowerBean();
                        BeanUtils.copyProperties(powerEntities.get(j), powerBean);
                        powerBeans.add(powerBean);
                    }
                    roleBean.setPowerBeans(powerBeans);
                    roleBeans.add(roleBean);
                }
                userBean.setRoleBeans(roleBeans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userBean;
    }
}
