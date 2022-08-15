package com01.jdbc.myjdbc;

//接口 : 实现Java的使用规范
// 让其他数据库实现这个接口 在创建对象的时候  直接创建对应的数据库对象 向上转型 多态
public interface jdbcInterface {
    //连接
     public Object  getConnection();
     //crud
    public void crud();
    //关闭连接
    public void closeInterface();
}
