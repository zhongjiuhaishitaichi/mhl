package com02MHL.domain;

//JavaBean 映射数据库的表
public class DiningTable {
    private Integer id;
    private String state;
    private String orderName;
    private String orderTEL;

    public DiningTable() {
    }

    public DiningTable(Integer id, String state, String orderName, String orderTEL) {
        this.id = id;
        this.state = state;
        this.orderName = orderName;
        this.orderTEL = orderTEL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getorderTEL() {
        return orderTEL;
    }

    public void setorderTEL(String orderTEL) {
        this.orderTEL = orderTEL;
    }

    @Override
    public String toString() {
        return "DiningTable{" +
                "id=" + id +
                ", state='" + state + '\'' +
                '}';
    }
}
