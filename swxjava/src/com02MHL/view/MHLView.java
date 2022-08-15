package com02MHL.view;

import com02MHL.domain.*;
import com02MHL.service.BillService;
import com02MHL.service.DiningTableService;
import com02MHL.service.EmployeeService;
import com02MHL.service.MenuService;
import com02MHL.utils.Utility;

import java.util.List;


//界面层 --> 业务层 -->DAO层 -->JavaBEan 数据库
public class MHLView {

    public static void main(String[] args) {
        new MHLView().mainMenu();
    }

    boolean loop = true;
    String key = "";
    //获得EmployeeService 属性 面向对象
    EmployeeService employeeService = new EmployeeService();
    //获得DiningTableService 属性
    DiningTableService diningTableService = new DiningTableService();
    //获得 MenuService 属性
    MenuService menuService = new MenuService();
    //BillService 属性
    private BillService billService = new BillService();


    //在view层 显示菜单的状态
    public void listDiningTable() {
        System.out.println(".................显示餐桌状态了..................");
        List<DiningTable> dingTableList = diningTableService.getDingTableList();
        for (DiningTable diningTable : dingTableList) {
            System.out.println(diningTable);
        }
    }

    //预定座位 座位不能不存在 不能不为空  预定
    public void orderDiningTable() {
        System.out.println("\n===============预定餐桌================");
        System.out.println("请选择预定餐桌的编号(-1)退出");
        int orderId = Utility.readInt();
        if (orderId == -1) {
            System.out.println("\n===============预定餐桌失败================");
            return; //不做处理直接返回 loop还是为true 还是在二级菜单
        }
        char c = Utility.readConfirmSelection();
        if (c == 'Y') {//表示要预定

            DiningTable diningTableId = diningTableService.getDiningTableId(orderId);
            if (diningTableId == null) {
                System.out.println("\n===============预定餐桌不存在================");
                return;
            }
            //字符串 equals方法!!!!!!
            if (diningTableId.getState().equals("空闲")||diningTableId.getState().equals("空")) {
                System.out.println("输入你的姓名.....");
                String name = Utility.readString(50);
                System.out.println("输入你的手机号.....");
                String TEL = Utility.readString(50);

                boolean orderDiningTable = diningTableService.orderDiningTable(orderId, name, TEL);
                if (orderDiningTable) {
                    System.out.println("==========恭喜预定成功=============");
                }
            }
        } else {
            System.out.println("\n===============预定餐桌失败================");

        }
    }

    //展示菜单信息
    public void getMenu() {
        List<FoodMenu> foodMenus = menuService.menuList();
        System.out.println(".................显示菜单了..................");
        for (FoodMenu foodMenu : foodMenus) {
            System.out.println(foodMenu);
        }
    }

    public void orderMenu() {
        //输入-1 直接返回
        System.out.println("==============点餐服务============");
        System.out.print("请输入点餐的桌号(-1退出): ");
        int orderDiningTableId = Utility.readInt();
        if (orderDiningTableId == -1) {
            System.out.println("==============取消点餐============");
            return;
        }
        System.out.print("请输入点餐的菜品号(-1退出): ");
        int orderMenuId = Utility.readInt();
        if (orderMenuId == -1) {
            System.out.println("==============取消点餐============");
            return;
        }
        System.out.print("请输入点餐的菜品量(-1退出): ");
        int orderNums = Utility.readInt();
        if (orderNums == -1) {
            System.out.println("==============取消点餐============");
            return;
        }
        //判断桌号是否存在 合理
        //返回的是对象
        DiningTable diningTable = diningTableService.getDiningTableId(orderDiningTableId);
        if (diningTable == null) {
            System.out.println("=============餐桌号不存在================");
            return;
        }
        FoodMenu menuById = menuService.getMenuById(orderMenuId);
        if (menuById == null) {
            System.out.println("=============菜品不存在================");
            return;
        }
        if (billService.orderMenu(orderMenuId, orderNums, orderDiningTableId)) {
            System.out.println("=============点餐成功================");
        } else {
            System.out.println("=============点餐失败================");
        }
    }
public void listBillMenu(){
    System.out.println("\n编号\t\t菜品号\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t\t状态\t\t\t菜品");
    List<MultiTable> billLists = billService.getBillList02();
    for(MultiTable multiTable: billLists){
        System.out.println(multiTable);
    }
}
public void payBill(){
    System.out.println("==============结账服务============");
    System.out.print("请选择要结账的餐桌编号(-1退出): ");
    int diningTableId = Utility.readInt();
    if (diningTableId == -1) {
        System.out.println("=============取消结账============");
        return;
    }
    //验证餐桌是否存在
    DiningTable diningTable = diningTableService.getDiningTableId(diningTableId);
    if (diningTable == null) {
        System.out.println("=============结账的餐桌不存在============");
        return;
    }
    //验证餐桌是否有需要结账的账单
    if (!(billService.hasPayBillByDiningTableId(diningTableId))) {
        System.out.println("=============该餐位没有未结账账单============");
        return;
    }
    System.out.print("结账方式(现金/支付宝/微信)回车表示退出: ");
    String payMode = Utility.readString(20, "");//说明如果回车，就是返回 ""
    if ("".equals(payMode)) {
        System.out.println("=============取消结账============");
        return;
    }
    char key = Utility.readConfirmSelection();
    if (key == 'Y') { //结账

        //调用我们写的方法
        if (billService.payBill(diningTableId, payMode)) {
            System.out.println("=============完成结账============");
        } else {
            System.out.println("=============结账失败============");
        }

    } else {
        System.out.println("=============取消结账============");
    }


}
    //显示主菜单
    public void mainMenu() {
        while (loop) {
            System.out.println("\n===============满汉楼================");
            System.out.println("\t\t 1 登录满汉楼");
            System.out.println("\t\t 2 退出满汉楼");
            System.out.print("请输入你的选择: ");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.print("输入员工号: ");
                    String empId = Utility.readString(50);
                    System.out.print("输入密  码: ");
                    String pwd = Utility.readString(50);

                    Employee employeeSingle = employeeService.getEmployeeSingle(empId, pwd);
                    if (employeeSingle != null) {
                        System.out.println("========登陆成功========   " + employeeSingle.getName());
                        //显示二级菜单, 这里二级菜单是循环操作，所以做成while
                        while (loop) {
                            System.out.println("\n===============满汉楼(二级菜单)================");
                            System.out.println("\t\t 1 显示餐桌状态");
                            System.out.println("\t\t 2 预定餐桌");
                            System.out.println("\t\t 3 显示所有菜品");
                            System.out.println("\t\t 4 点餐服务");
                            System.out.println("\t\t 5 查看账单");
                            System.out.println("\t\t 6 结账");
                            System.out.println("\t\t 9 退出满汉楼");
                            System.out.print("请输入你的选择: ");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    listDiningTable();
                                    break;
                                case "2":
                                    orderDiningTable();
                                    break;
                                case "3":
                                    getMenu();
                                    break;
                                case "4":
                                   orderMenu();
                                    break;
                                case "5":
                                 listBillMenu(); //显示所有账单
                                    break;
                                case "6":
                                   payBill();
                                    break;
                                case "9":
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("你的输入有误，请重新输入");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("===============登录失败================");
                    }
                    break;
                case "2":
                    loop = false;//退出
                    break;
                default:
                    System.out.println("你输入有误，请重新输入.");
            }
        }
        System.out.println("你退出了满汉楼系统~");
    }

}
