package com01.jdbc.JDBC01;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


//jdbc 的入门
public class jdbc01 {
    public static void main(String[] args) throws SQLException {
        //需要先引包  import com.mysql.jdbc.Driver;

        //创建驱动
        Driver driver = new Driver();
        //得到连接  用java连接到mysql
        // 规定:jdbc:mysql://  localhost代表主机(IP) : 端口  哪个数据库
        String url = "jdbc:mysql://localhost:3306/swx_db01";
        //用户名和密码放在 properties对象里面  user password
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "swx");
        Connection connect = driver.connect(url, properties);

        //执行sql
        // String sql = "insert into actor values(null,'宇彤','女','2000-12-12','123')";
       // String sql = "update actor set name='时文宣' where id=1";
        String sql = "delete from actor where id=1";
        //statement 用于执行静态的sql 语句 并返回生成的对象
        Statement statement = connect.createStatement();
        int i = statement.executeUpdate(sql);// 如果是dml语句 返回的是影响的行数  假如执行了就会返回1(这里是一条sql)
        System.out.println(i > 0 ? "成功" : "失败");

        //关闭资源
        statement.close();
        connect.close();

    }
}
