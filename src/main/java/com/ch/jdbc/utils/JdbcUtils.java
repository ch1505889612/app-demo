package com.ch.jdbc.utils;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {

    private static Properties properties=new Properties();

    static {
        //加载配置文件
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //加载驱动
        try {
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
        return connection;
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        try {
           if (connection!=null){
               connection.close();
           }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (statement!=null){
                statement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (resultSet!=null){
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void close(Connection connection, Statement statement){
      close(connection,statement,null);
}


}