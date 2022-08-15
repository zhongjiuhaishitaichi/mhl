package com01.jdbc.JDBC01;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@SuppressWarnings({"all"})
public class homework {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        //连接到我的数据库

        //通过Properties对象获取配置文件的信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        //获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        Class.forName(driver);//连接驱动
        Connection connection = DriverManager.getConnection(url, user, password);

        //sql
        Statement statement = connection.createStatement();
       // String sql = "insert into news values(null,'烟台新闻','2000-10-10')";
      //  String sql ="update news set NAME ='苹果发布会'";
        String sql= "delete  from news where id=1";
        int i = statement.executeUpdate(sql);
        System.out.println(i);

        //关闭资源
        statement.close();
        connection.close();
    }
}
