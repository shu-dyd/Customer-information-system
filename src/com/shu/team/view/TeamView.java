package com.shu.team.view;

import com.shu.team.domain.Employee;
import com.shu.team.domain.Programmer;
import com.shu.team.service.NameListService;
import com.shu.team.service.TeamException;
import com.shu.team.service.TeamService;

public class TeamView {
    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();

    public void enterMainMenu(){
        boolean loopFlag = true;
        while (loopFlag){
            char menu = '0';
            if(menu != '1'){
                listAllEmployee();
            }
            System.out.print("1-团队列表 2-添加团队成员 3-删除团队成员 4-退出 请选择(1-4):");
            menu = TSUtility.readMenuSelection();
            switch (menu){
                case '1':
                    getTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.println("确认是否退出(Y/N)：");
                    char isExit = TSUtility.readConfirmSelection();
                    if(isExit == 'Y'){
                        loopFlag = false;
                    }
                    break;
            }
        }
            }
    //显示所有员工信息
    private void listAllEmployee(){
        System.out.println("-------------------------------------------开发团队调度软件----------------------------------------------");
        Employee[] employees = listSvc.getAllEmployees();
        System.out.println("ID\t\t姓名\t\t\t年龄\t\t工资\t\t\t职位\t\t\t状态\t\t\t奖金\t\t\t股票\t\t\t设备");
        for(int i = 0; i < employees.length; i++){
            System.out.println(employees[i]);
        }
        System.out.println("------------------------------------------------------------------------------------------------------");
    }
    private void getTeam(){

        System.out.println("-----------------------------团队成员列表-----------------------------");
        Programmer[] team = teamSvc.getTeam();
        if(team.length == 0){
            System.out.println("开发团队没有成员");
        }else{
            System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票\n");
            for(int i = 0; i < team.length; i++){
                System.out.println(team[i].getDetailsForTeam());
            }
        }
        System.out.println("--------------------------------------------------------------------");
        TSUtility.readReturn();
    }
    private void addMember(){

        System.out.println("-------------------------------添加成员-------------------------------");
        System.out.print("输入要添加员工的ID：");
        int id = TSUtility.readInt();
        try {
            //注意声明在try内部的变量没有办法在外面继续被调用
            Employee emp = listSvc.getEmployee(id);
            teamSvc.addMember(emp);
            System.out.println("添加成功");

        } catch (TeamException e) {
            System.out.println("添加失败，原因：" + e.getMessage());
        }
        //正常情况下，将代码放在try-catch后面是有风险的，有可能catch中又异常，或者直接在try和catch中return
        //这里没关系因为始终
        TSUtility.readReturn();

    }
    private void deleteMember(){
        System.out.println("------------------------------删除成员---------------------------------");
        System.out.print("请输入要删除员工的TID:");
        int memberId = TSUtility.readInt();

        System.out.println("确认是否删除(Y/N):");
        char isDelete = TSUtility.readConfirmSelection();

        if(isDelete == 'N'){
            return;
        }
        try {
            teamSvc.removeMember(memberId);
            System.out.println("删除成功");
        } catch (TeamException e) {
            System.out.println("删除失败，原因：" + e.getMessage());
        }
        TSUtility.readReturn();
    }

    public static void main(String[] args) {
        TeamView teamView = new TeamView();
        teamView.enterMainMenu();
    }

}
