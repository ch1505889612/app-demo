package com.ch.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestJdbc2 {

    public static void main(String[] args) {
        Connection connection = Connections.getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            //发送数据
             preparedStatement = connection.prepareStatement("select * from emp");
            //返回一个结果集
             resultSet = preparedStatement.executeQuery();
           while (resultSet.next()){
               int emptno = resultSet.getInt(1);
               System.out.println(emptno);
           }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            try {
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (preparedStatement!=null){
                    preparedStatement.close();
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
