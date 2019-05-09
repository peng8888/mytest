package com.itheima.mybatis.dao;

import com.itheima.mybatis.po.User;

import java.util.List;

//定义一个接口
public interface UserDao {
    //接口的抽象方法
    User getUserById(int id);
    List<User> getUserByName(String username);
    void insertUser(User user);

}
