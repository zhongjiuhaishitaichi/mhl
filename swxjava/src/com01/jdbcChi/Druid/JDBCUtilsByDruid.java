package com01.jdbcChi.Druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtilsByDruid {
    public static DataSource dataSource=null;
    //静态代码块 完成 读取配置文件 创建德鲁伊池
    static{
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src\\druid.properties"));
            dataSource= DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //返回连接的方法
    public static Connection getConnection() throws SQLException {
        return  dataSource.getConnection();
    }
    //关闭各种连接  注意这里的关闭connection 不是关闭连接
    // 在数据库连接池技术中，close 不是真的断掉连接
    //  而是把使用的Connection对象放回连接池
    public static  void  close(ResultSet resultSet, Statement statement,Connection connection) throws SQLException {
        try {
            if (resultSet!=null){
                resultSet.close();
            }
            if (statement!=null){
                statement.close();
            }
            if (connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
