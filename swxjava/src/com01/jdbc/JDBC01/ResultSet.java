package com01.jdbc.JDBC01;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

@SuppressWarnings({"all"})
public class ResultSet {

    public static void main(String[] args) throws Exception {

        //通过Properties对象获取配置文件的信息

        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        //获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        //1. 注册驱动
        Class.forName(driver);//建议写上

        //2. 得到连接
        Connection connection = DriverManager.getConnection(url, user, password);

        //3. 得到Statement
        Statement statement = connection.createStatement();
        //4. 组织SqL
        String sql = "select id, NAME , sex, borndate ,phone from actor";
        //执行给定的SQL语句，该语句返回单个 ResultSet对象
        java.sql.ResultSet resultSet = statement.executeQuery(sql);

        //5. 使用while取出数据
        while(resultSet.next()){      // 让光标向后移动，如果没有更多行，则返回false
            int anInt = resultSet.getInt(1);//获取该行的第1列
            String name = resultSet.getString(2);//获取该行的第2列
            String sex = resultSet.getString(3);//获取该行的第3列
            String borndate = resultSet.getString(4);//获取该行的第4列
            String phone = resultSet.getString(5);

            System.out.println(anInt + "\t" + name + "\t" + sex + "\t" + borndate+"\t"+phone);
        }

        //6. 关闭连接
        resultSet.close();
        statement.close();
        connection.close();

    }
}

