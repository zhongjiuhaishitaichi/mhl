package com01.jdbc.myjdbc;

public class mysqlJDBC implements jdbcInterface {

    @Override
    public Object getConnection() {
        System.out.println("MySQL数据库连接成功");
        return null;
    }

    @Override
    public void crud() {

    }

    @Override
    public void closeInterface() {
        System.out.println("mysql数据库关闭");
    }
}
