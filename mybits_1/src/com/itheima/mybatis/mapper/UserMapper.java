package com.itheima.mybatis.mapper;

import com.itheima.mybatis.po.User;

import java.util.List;

public interface UserMapper {
    //接口的抽象方法
    User getUserById(int id);
    List<User> getUserByName(String username);
    void insertUser(User user);
}
