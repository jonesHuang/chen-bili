package com.chen.bili.service;

import com.chen.bili.dao.UserDao;
import com.chen.bili.domain.User;
import com.chen.bili.domain.UserInfo;
import com.chen.bili.domain.constant.UserConstant;
import com.chen.bili.domain.exception.ConditionException;
import com.chen.bili.service.util.MD5Util;
import com.chen.bili.service.util.RSAUtil;
import com.chen.bili.service.util.TokenUtil;
import com.mysql.cj.util.StringUtils;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author ChenYi
 * @corporation HongYang_software
 * @create 2022-01-12
 */

@Service//注入服务型的类
public class UserService {

    @Autowired
    private UserDao userDao;

    public void addUser(User user){
        String phone=user.getPhone();
        if (StringUtils.isNullOrEmpty(phone)){
            throw new ConditionException("手机号不能为空");
        }
        User dbUser = this.getUserByPhone(phone);
        if (dbUser != null){
            throw new ConditionException("该手机号已被注册");
        }
        Date now = new Date();
        String salt = String.valueOf(now.getTime());
        String password = user.getPassword();
        String rawPassword;
        try {
            rawPassword = RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new ConditionException("密码解密失败");
        }
        String md5password = MD5Util.sign(rawPassword,salt,"UTF-8");
        user.setSalt(salt);
        user.setPassword(md5password);
        user.setCreateTime(now);
        userDao.addUser(user);
        //添加用户信息
        UserInfo userInfo=new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setNickname(UserConstant.DEFAULT_NICKNAME);
        userInfo.setBirth(UserConstant.DEFAULT_BIRTH);
        userInfo.setGender(UserConstant.GENDER_MALE);
        userInfo.setCreateTime(now);
        userDao.addUserInfo(userInfo);
    }

    public User getUserByPhone(String phone){
        return userDao.getUserByPhone(phone);
    }

    public String login(User user) throws Exception{
        String phone = user.getPhone();
        if (StringUtils.isNullOrEmpty(phone)){
            throw new ConditionException("手机号不能为空");
        }
        User dbUser = this.getUserByPhone(phone);
        if(dbUser == null){
            throw new ConditionException("该用户不存在");
        }
        String password = user.getPassword();
        String rawPassword ;
        try {
            rawPassword = RSAUtil.decrypt(password);
        }catch (Exception e){
            throw new ConditionException("密码解密失败");
        }
        String salt = dbUser.getSalt();
        String md5password = MD5Util.sign(rawPassword,salt,"UTF-8");
        if (!md5password.equals(dbUser.getPassword())){
            throw new ConditionException("密码错误!");
        }TokenUtil tokenUtil = new TokenUtil();

        return TokenUtil.generateToken(dbUser.getId());
    }

    public User getUserInfo(Long userId){
        User user=userDao.getUserById(userId);
        UserInfo userInfo = userDao.getUserInfoByUserId(userId);
        user.setUserInfo(userInfo);
        return user;
    }

}
