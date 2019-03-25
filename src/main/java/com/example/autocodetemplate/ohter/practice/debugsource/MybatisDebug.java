package com.example.autocodetemplate.ohter.practice.debugsource;

import com.example.autocodetemplate.dao.SysAppVersionInfoDao;
import com.example.autocodetemplate.domain.SysAppVersionInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class MybatisDebug {
    public static void main(String[] args) throws Exception{
        String resource="/mybatis/mybatis-config.xml";
        MybatisDebug mybatisDebug = new MybatisDebug();
        InputStream inputStream = null;

        inputStream = mybatisDebug.getClass().getResourceAsStream(resource);

        ResourceBundle resourceBundle = new PropertyResourceBundle(inputStream);
        resourceBundle.getString("configuration");

        SqlSessionFactory sqlSessionFactory=null;
        sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession=null;
        try {
            sqlSession=sqlSessionFactory.openSession();
            SysAppVersionInfoDao sysAppVersionInfoDao = sqlSession.getMapper(SysAppVersionInfoDao.class);
            SysAppVersionInfo role = sysAppVersionInfoDao.queryByIdBySelectProvider(16);
            System.out.println(role.getId()+":"+role.getRemark()+":"+role.getAppVersion());
            sqlSession.commit();

        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }
}
