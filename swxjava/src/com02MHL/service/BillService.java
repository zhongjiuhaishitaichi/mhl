package com02MHL.service;

import com02MHL.DAO.BillDAO;
import com02MHL.DAO.MultiTableDAO;
import com02MHL.domain.Bill;
import com02MHL.domain.MultiTable;

import java.util.List;
import java.util.UUID;

//处理账单
public class BillService {
    private BillDAO billDAO = new BillDAO();
    //得到MenuService 属性
    private MenuService menuService = new MenuService();
    private DiningTableService diningTableService = new DiningTableService();

    //多表查询的 对象属性
    private MultiTableDAO multiTableDAO=new MultiTableDAO();

    //方法 生成账单 需要更新对应的餐桌状态  boolean
    public boolean orderMenu(int menuId, int nums, int diningTableId) {
        //避免账单号重复
        String billID = UUID.randomUUID().toString();//UUID 返回随机生成的字符串

        //账单生成到bill表
        //menuService.getMenuById(menuId).getPrice()*nums 因为一次只能点一样东西 所以可以这样写
        int update = billDAO.update("insert into bill values(null,?,?,?,?,?,now(),'未结账')",
                billID, menuId, nums, menuService.getMenuById(menuId).getPrice() * nums, diningTableId);


        if (update <= 0)
            return false;
        //点菜了 更新餐桌状态
        return diningTableService.updateDiningTableState(diningTableId, "就餐中");
    }

    //显示账单信息
    public List<Bill> getBillList() {
        return billDAO.queryMulti("select * from bill", Bill.class);
    }
    //进阶版 多表查询 一样是用JavaBean 多添加属性 当作一个表的映射
    // 带菜品名的
    public List<MultiTable> getBillList02() {
        //注意这里的多表查询 AS name2
        return multiTableDAO.queryMulti("SELECT bill.*,NAME as name2 FROM bill,menu WHERE bill.menuId=menu.id", MultiTable.class);
    }

    //查看某个 餐桌是否有未结账的账单 就查一个 桌的
    public boolean hasPayBillByDiningTableId(int diningTableId) {
        Bill bill = billDAO.querySingle
                ("select * from bill where diningTableId=? and state='未结账'", Bill.class, diningTableId);
        return bill != null;
    }
    //如果该餐桌存在 并且有未结账的 就调用方法
    public boolean payBill(int diningTableId,String payMode){
        //修改bill表
        int update = billDAO.update
                ("update bill set state=? where diningTableId=? and state='未结账'", payMode, diningTableId);
        if (update<=0){// 表示修改失败 返回
            return  false;
        }
        //得到返回 是否是空闲的桌子
        boolean stateToFree = diningTableService.updateDiningTableStateToFree(diningTableId, "空闲");
        if (!stateToFree)
            return false;
        return true;
    }
}
