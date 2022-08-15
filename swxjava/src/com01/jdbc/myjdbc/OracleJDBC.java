package com01.jdbc.myjdbc;

public class OracleJDBC implements jdbcInterface  {
    @Override
    public Object getConnection() {
        System.out.printf("Oracle 数据库连接");
        return null;
    }

    @Override
    public void crud() {

    }

    @Override
    public void closeInterface() {
        System.out.println("Oracle 数据库关闭");
    }
}
