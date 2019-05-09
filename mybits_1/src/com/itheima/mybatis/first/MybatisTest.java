package com.itheima.mybatis.first;

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

public class MybatisTest {
    private  SqlSessionFactory sqlSessionFactory = null;
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
    public void getUserById() throws Exception{

        // 第四步：创建一个SQLSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 第五步：使用SqlSession对象执行查询，得到User对象。
        // 第一个参数：执行查询的statementId
        User user = sqlSession.selectOne("getUserById", 10);
        // 第六步：打印结果
        System.out.println(user);
        // 第七步：释放资源
        sqlSession.close();
    }
    @Test
    public void getUserByName()throws Exception{
        //创建一个SQLsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行查询
        List<User> list = sqlSession.selectList("getUserByName", "张");
        for (User user : list) {
            System.out.println(user);
        }
        //释放资源
        sqlSession.close();

    }
    @Test
    public void addUser()throws Exception{
        //创建一个SQLsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建User对象
        User user = new User();
        user.setUsername("小乔");
        user.setBirthday(new Date());
        user.setSex("2");
        user.setAddress("上海");
        //插入用户
        sqlSession.insert("insertUser",user);
        System.out.println(user.getId());
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }
    @Test
    public void deleteUser()throws Exception{
        //创建一个SQLsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //删除用户
        sqlSession.delete("deleteUser",45);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();

    }
    @Test
    public void updateUser(){
        //创建一个SQLsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建User对象
        User user = new User();
        user.setUsername("张角1");
        user.setId(27);
        //更新用户
        sqlSession.update("updateUser",user);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }
}
