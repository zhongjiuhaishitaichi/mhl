package com02MHL.domain;


//使用这个类做映射 包含所有的需要的字段 多表的--->当作一个表使用
// sql语句多表查询 避免笛卡儿集
public class MultiTable {
    private Integer id;
    private String billId;
    private String menuId;
    private String nums;
    private String money;
    private String diningTableId;
    private String billDate;
    private String state;
    private String name2;

    //Javabean 映射表的对应的字段 比如name2 在映射成类的时候 对应setName2() 方法
    //当有多表查询 他们有多个字段是一个的 但是代表含义不一样  name: 菜名 员工名  这里属性就会出问题
    // 解决: 在组织sql时 AS 别名 别名就是对应字段名 就可以避免重复

    public MultiTable() {
    }

    public MultiTable(Integer id, String billId, String menuId, String nums, String money, String diningTableId, String billDate, String state, String name) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.nums = nums;
        this.money = money;
        this.diningTableId = diningTableId;
        this.billDate = billDate;
        this.state = state;
        this.name2 = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name) {
        this.name2 = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(String diningTableId) {
        this.diningTableId = diningTableId;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return  id +
                "\t\t" + menuId +
                "\t\t\t" + nums +
                "\t\t\t" + money +
                "\t\t" + diningTableId +
                "\t\t" + billDate +
                "\t\t" + state +
                "\t\t" + name2 ;
    }
}
