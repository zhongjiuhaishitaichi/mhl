package com02MHL.service;

import com02MHL.DAO.diningTableDAO;
import com02MHL.domain.DiningTable;

import java.util.List;

public class DiningTableService {

    private diningTableDAO diningTableDAO = new diningTableDAO();

    //返回餐桌信息
    public List<DiningTable> getDingTableList() {

        return diningTableDAO.queryMulti("select id,state from diningTable", DiningTable.class);
    }

    //根据id 返回对象
    //想要预定的餐桌号
    public DiningTable getDiningTableId(int id) {
        return diningTableDAO.querySingle("select * from diningTable where id=?", DiningTable.class, id);
    }

    //如果餐桌被预定 调用方法 对餐桌信息进行修改
    public boolean orderDiningTable(int id, String orderName, String orderTel) {
        int update =
                diningTableDAO.update("update diningTable set state='已预定',orderName=?,orderTel=? where id=?", orderName, orderTel, id);
        return update > 0;
    }

    //生成账单的同时需要更新餐桌的状态
    // 在吃 吃完了....
    public boolean updateDiningTableState(int id, String state) {
        int update = diningTableDAO.update("update diningTable set state =? where id=?", state, id);
        return update > 0;
    }
    //实现 把结完帐的餐桌设置为空闲
    public boolean updateDiningTableStateToFree(int id, String state) {
        int update = diningTableDAO.update("update diningTable set state =?,orderName='',orderTel='' where id=?", state, id);
        return update > 0;
    }
}
