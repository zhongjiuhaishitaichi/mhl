package com01.jdbcChi.Druid_DButils;


import com01.jdbcChi.Druid.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings({"all"})
public class DBUtils {

        //使用apache-DBUtils 工具类 + druid 完成对表的crud操作
        @Test
        public void testQueryMany() throws SQLException { //返回结果是多行的情况

            //1. 得到 连接 (druid)
            Connection connection = JDBCUtilsByDruid.getConnection();
            //2. 使用 DBUtils 类和接口 , 先引入DBUtils 相关的jar , 加入到本Project
            //3. 创建 QueryRunner
            QueryRunner queryRunner = new QueryRunner();
            //4. 就可以执行相关的方法，返回ArrayList 结果集
            String sql = "select * from actor where id >= ?";
            //   注意: sql 语句也可以查询部分列
          //  String sql = "select id, name from actor where id >= ?";
            // 老韩解读
            //(1) query 方法就是执行sql 语句，得到resultset ---封装到 --> ArrayList 集合中
            //(2) 返回集合
            //(3) connection: 连接
            //(4) sql : 执行的sql语句
            //(5) new BeanListHandler<>(Actor.class): 在将resultset -> Actor 对象 -> 封装到 ArrayList
            //    底层使用反射机制 去获取Actor 类的属性，然后进行封装
            //(6) 1 就是给 sql 语句中的? 赋值，可以有多个值，因为是可变参数Object... params
            //(7) 底层得到的resultset ,会在query 关闭, 关闭PreparedStatment
            List<Actor> query = queryRunner.query(connection, sql, new BeanListHandler<>(Actor.class), 1);
            System.out.println("输出集合的信息");
            for (Actor actor : query) {
                System.out.print(actor);
            }


            //释放资源
            JDBCUtilsByDruid.close(null, null, connection);
            /**
             * 分析 queryRunner.query方法:
             * public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
             *         PreparedStatement stmt = null;//定义PreparedStatement
             *         ResultSet rs = null;//接收返回的 ResultSet
             *         Object result = null;//返回ArrayList
             *
             *         try {
             *             stmt = this.prepareStatement(conn, sql);//创建PreparedStatement
             *             this.fillStatement(stmt, params);//对sql 进行 ? 赋值
             *             rs = this.wrap(stmt.executeQuery());//执行sql,返回resultset
             *             result = rsh.handle(rs);//返回的resultset --> arrayList[result] [使用到反射，对传入class对象处理]
             *         } catch (SQLException var33) {
             *             this.rethrow(var33, sql, params);
             *         } finally {
             *             try {
             *                 this.close(rs);//关闭resultset
             *             } finally {
             *                 this.close((Statement)stmt);//关闭preparedstatement对象
             *             }
             *         }
             *
             *         return result;
             *     }
             */


        }

    //演示 apache-dbutils + druid 完成 返回的结果是单行记录(单个对象)
    @Test
    public void testQuerySingle() throws SQLException {

        //1. 得到 连接 (druid)
        Connection connection = JDBCUtilsByDruid.getConnection();
        //2. 使用 DBUtils 类和接口 , 先引入DBUtils 相关的jar , 加入到本Project
        //3. 创建 QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        //4. 就可以执行相关的方法，返回单个对象
        String sql = "select * from actor where id = ?";
        // 老韩解读
        // 因为我们返回的单行记录<--->单个对象 , 使用的Hander 是 BeanHandler
        Actor actor = queryRunner.query(connection, sql, new BeanHandler<>(Actor.class), 10);
        System.out.println(actor);

        // 释放资源
        JDBCUtilsByDruid.close(null, null, connection);

    }

    //演示apache-dbutils + druid 完成查询结果是单行单列-返回的就是object
    @Test
    public void testScalar() throws SQLException {

        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select name from actor where id = ?";
        Object query = queryRunner.query(connection, sql, new ScalarHandler(), 3);
        System.out.println(query);
        JDBCUtilsByDruid.close(null,null,connection);
    }

    //演示apache-dbutils + druid 完成 dml (update, insert ,delete)
    @Test
    public void testDML() throws SQLException {

        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
       // String sql="update actor set name=? where id=?";
        String sql="insert into actor values(null,?,?,?,?)";
        //老韩解读
        //(1) 执行dml 操作是 queryRunner.update()
        //(2) 返回的值是受影响的行数 (affected: 受影响)
        //int affectedRow = queryRunner.update(connection, sql, "刘禹锡", 4);
        int affectedRow = queryRunner.update(connection, sql, "宇彤", "女", "2323-12-10", "13023");
        System.out.println(affectedRow > 0 ? "执行成功" : "执行没有影响到表");

        // 释放资源
        JDBCUtilsByDruid.close(null, null, connection);

    }

}