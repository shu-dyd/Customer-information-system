package com.shu.team.service;

import com.shu.team.domain.*;

import static com.shu.team.service.Data.*;//导入某个类的静态结构

public class NameListService {
    private Employee[] employees;

    public NameListService() {
        employees = new Employee[EMPLOYEES.length];
        for(int i = 0; i < EMPLOYEES.length; i++){
            int type = Integer.parseInt(EMPLOYEES[i][0]);//利用包装类将字符串类型转变成int类型
            int id = Integer.parseInt(EMPLOYEES[i][1]);
            String name = EMPLOYEES[i][2];
            int age = Integer.parseInt(EMPLOYEES[i][3]);
            double salary = Double.parseDouble(EMPLOYEES[i][4]);
            Equipment equipment;//防止声明变量重名，声明在外面
            double bonus;
            int stock;
            switch (type){
                case EMPLOYEE:
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case PROGRAMMER:
                    equipment = createEquipment(i);
                    employees[i] = new Programmer(id, name, age, salary, equipment);
                    break;
                case DESIGNER:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    employees[i] = new Designer(id, name, age, salary, equipment, bonus);
                    break;
                case ARCHITECT:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    stock = Integer.parseInt(EMPLOYEES[i][6]);
                    employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
                    break;
            }
        }
    }
    //获取指定位置员工的设备
    private Equipment createEquipment(int i) {
        int type = Integer.parseInt(EQUIPMENTS[i][0]);
        String model = EQUIPMENTS[i][1];
        switch (type){
            case PC:
                String display = EQUIPMENTS[i][2];
                return new PC(model, display);
            case NOTEBOOK:
                double price = Double.parseDouble(EQUIPMENTS[i][2]);
                return new NoteBook(model, price);
        }
        return null;
    }

    public Employee[] getAllEmployees(){
        return employees;
    }
    public Employee getEmployee(int id) throws TeamException{
        for(int i = 0; i < employees.length; i++){
            if(employees[i].getId() == id){//如果有两个都是包装类的话要用equals来比较防止超过范围，只要有一个是基本数据类型就可以用==比较
                return employees[i];
            }
        }
        throw new TeamException("找不到指定员工");
    }
}
