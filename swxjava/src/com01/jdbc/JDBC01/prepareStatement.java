package com01.jdbc.JDBC01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class prepareStatement {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Scanner scanner = new Scanner(System.in);
        //让用户输入管理员名和密码
        System.out.print("请输入管理员的名字: ");  //next(): 当接收到 空格或者 '就是表示结束  newLine() 是遇到enter才换行
        String admin_name = scanner.nextLine(); // 如果希望看到SQL注入，这里需要用nextLine
        System.out.print("请输入管理员的密码: ");
        String admin_pwd = scanner.nextLine();
        //读取配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        //获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        //注册驱动
        Class.forName(driver);
        //获得连接
        Connection connection = DriverManager.getConnection(url, user, password);
        //3. 得到PreparedStatement
        //3.1 组织SqL , Sql 语句的 ? 就相当于占位符
       String sql="select NAME,pwd from admin where NAME=? and pwd=?";
        //3.2 preparedStatement 对象实现了 PreparedStatement 接口的实现类的对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //3.3 给 ? 赋值
            preparedStatement.setString(1,admin_name);
            preparedStatement.setString(2,admin_pwd);

        //4. 执行 select 语句使用  executeQuery
        //   如果执行的是 dml(update, insert ,delete)    executeUpdate()
        //   这里执行 executeQuery ,不要在写 sql!!!!!!!!
        ResultSet resultSet = preparedStatement.executeQuery();


        //显示成员名单
        if (resultSet.next()){
            System.out.println("登陆成功");
        }else {
            System.out.println("登陆失败");
        }
        String sql02="select NAME,pwd from admin";
        ResultSet resultSet1 = preparedStatement.executeQuery(sql02);

        while(resultSet1.next()){
            String name = resultSet1.getString(1);
            String pwd = resultSet1.getString(2);
            System.out.println("name   "+name+ "  pwd    "+pwd);
        }

        
        //关闭连接
        resultSet.close();
        preparedStatement.close();
        connection.close();

    }
}
