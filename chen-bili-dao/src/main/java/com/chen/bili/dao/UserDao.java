package com.chen.bili.dao;

import com.chen.bili.domain.User;
import com.chen.bili.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ChenYi
 * @corporation HongYang_software
 * @create 2022-01-12
 */

@Mapper
public interface UserDao {


    Integer addUser(User user);

    Integer addUserInfo(UserInfo userInfo);

    User getUserByPhone(String phone);

    User getUserById(Long userId);

    UserInfo getUserInfoByUserId(Long userId);
}
