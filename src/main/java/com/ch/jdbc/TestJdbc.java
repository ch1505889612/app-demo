package com.ch.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class TestJdbc {



    public static void main(String[] args) throws IOException {

        Properties pro=new Properties();
      //  InputStream stream = TestJdbc.class.getClassLoader().getResourceAsStream("jdbc.properties");
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
        //加载配置文件
        pro.load(stream);

        System.out.println(pro.getProperty("username")+"---"+pro.getProperty("password"));

        Statement statement=null;
        ResultSet resultSet=null;
        //1.加载驱动 (选择数据库)
        try {
            Class.forName(pro.getProperty("driver"));
            //        2.建立连接 (获取连接)
            Connection conn = DriverManager.getConnection(
                    pro.getProperty("url"),
                    pro.getProperty("username"),
                    pro.getProperty("password")
            );
            //        3.准备sql
            String sql="select * from dept";
            //        4.封装处理块
             statement = conn.createStatement();
            //        5.发送执行,得到结果
             resultSet = statement.executeQuery(sql);
            //        6.处理结果
            while (resultSet.next()){
                int deptno = resultSet.getInt(1);
                String dname = resultSet.getString(2);
                String lcl= resultSet.getString(3);
                System.out.println(deptno+"---->"+dname+"---->"+lcl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //        7.关闭
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }
}
