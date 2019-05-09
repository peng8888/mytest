package com.itheima.mybatis.dao;

import com.itheima.mybatis.dao.impl.UserDaoImpl;
import com.itheima.mybatis.mapper.UserMapper;
import com.itheima.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserMapperTest {
    private SqlSessionFactory sqlSessionFactory = null;
    //初始化方法
    @Before
    public void Init()throws Exception{
        // 第一步：创建一个SQLSessionFactoryBuilder对象。
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //第二步：加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 第三步：创建一个SQLSessionFactory
        sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }
    @Test
    public void testGetUserById(){

        // 第四步：创建一个SQLSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserById(46);
        System.out.println(user);
        //释放资源
        sqlSession.close();
    }
    @Test
    public void testGetUserByName(){

        // 第四步：创建一个SQLSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.getUserByName("张");
        for (User user : list) {
            System.out.println(user);
        }
        sqlSession.close();
    }
    @Test
    public void testInsertUser(){
        //创建实现类对象
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = new User();
        user.setUsername("赵云");
        user.setAddress("正定");
        user.setBirthday(new Date());
        user.setSex("1");
        userDao.insertUser(user);
    }



}
