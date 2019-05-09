package com.itheima.mybatis.dao.impl;

import com.itheima.mybatis.dao.UserDao;
import com.itheima.mybatis.po.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao{
    //通过构造方法注入sqlSessionFactory
    //有了sqlSessionFactory我们就可以对数据库进行操作
    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User getUserById(int id) {
        //有了sqlSessionFactory我们就可以对数据库进行操作
        // 第四步：创建一个SQLSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //根据ID查询用户信息
        User user = sqlSession.selectOne("getUserById", id);
        //关闭SQLsession
        sqlSession.close();
        return user;
    }

    @Override
    public List<User> getUserByName(String username) {
        //创建一个SQLsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行查询
        List<User> list = sqlSession.selectList("getUserByName", username);

        //释放资源
        sqlSession.close();

        return list;
    }

    @Override
    public void insertUser(User user) {
        //创建一个SQLsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //插入用户
        sqlSession.insert("insertUser",user);

        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }
}
