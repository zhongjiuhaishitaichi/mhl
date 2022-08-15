package com02MHL.service;

import com02MHL.DAO.FoodMenuDAO;
import com02MHL.domain.FoodMenu;

import java.awt.*;
import java.util.List;

public class MenuService {

    private FoodMenuDAO foodMenuDAO=new FoodMenuDAO();

    //返回菜单
    public List<FoodMenu> menuList(){
        return foodMenuDAO.queryMulti("select * from menu",FoodMenu.class);
    }
    //需要一个 方法 根据id 返回Menu对象
    public FoodMenu getMenuById(int id){
        return foodMenuDAO.querySingle("select * from menu where id=?",FoodMenu.class,id);
    }
}
