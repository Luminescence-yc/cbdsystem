package com.project.service;

import com.project.bean.UserBean;
import org.springframework.stereotype.Service;

/**
 * @author 万晓川
 * @version v1.0
 * @ClassName IUserService
 * @Description 用户业务接口
 * @date 2019年05月30日 14:28
 */
public interface IUserService {
    /**
     * 根据用户名查找用户Bean
     *
     * @param username 用户名
     * @return 用户Bean
     */
    public UserBean findByUsername(String username);
}
