package com01.jdbc.myjdbc;

public class TestJDBC {
    public static void main(String[] args) {
        jdbcInterface jdbcInterface = new OracleJDBC();
        jdbcInterface.getConnection();//动态绑定
        jdbcInterface.crud();
        jdbcInterface.closeInterface();
    }
}
