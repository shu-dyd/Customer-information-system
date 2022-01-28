package com.shu.team.service;

import com.shu.team.domain.Architect;
import com.shu.team.domain.Designer;
import com.shu.team.domain.Employee;
import com.shu.team.domain.Programmer;

import java.awt.print.PrinterIOException;

public class TeamService {
    private static int counter = 1;// 给memberid赋值使用
    private final int MAX_MEMBER = 5;// 限制开发团队人数
    private Programmer[] team = new Programmer[MAX_MEMBER];// 保存开发团队成员
    private int total;// 记录开发团队中实际的人数

    public Programmer[] getTeam(){
        Programmer[] team = new Programmer[total];
        for(int i = 0; i < team.length; i++){
            team[i] = this.team[i];
        }
        return team;
    }

    public void addMember(Employee e) throws TeamException{
        if(total >= MAX_MEMBER){
            throw new TeamException("成员已满，无法添加");
        }
        if(!(e instanceof Programmer)){
            throw new TeamException("该成员不是开发人员，无法添加");
        }
        if(isExist(e)){
            throw new TeamException("该成员已在本开发团队中");
        }
        // 向下转型，对象多态，父类只能调用自己声明的属性和方法，这里想要用Programmer中的getStatus()就需要向下转型。
        // 另外，对象子类变量父类时，如果子类中重写了父类的同名方法，就会调用子类重写的方法，但是如果是属性，即使子类有同名属性还是会用父类的。
        Programmer p = (Programmer) e;
        // 比较两个字符串注意使用equals而不是==
        if("BUSY".equals(p.getStatus().getNAME())){
            throw new TeamException("该员工已是某团队成员");
        }else if("VOCATION".equals(p.getStatus().getNAME())){
            throw new TeamException("该员工正在休假，无法添加");
        }

        // 获取team已有成员中的架构师、设计师、程序员的人数。好吃是只要遍历一次，不用每次需要判断的时候都去遍历一次
        int numOfArch = 0, numOfDes = 0, numOfPro = 0;
        for(int i = 0; i < total; i++){
            // 先判断范围小的，不然每个人都是程序员
            if(team[i] instanceof Architect){
                numOfArch++;
            }else if(team[i] instanceof Designer){
                numOfDes++;
            }else if(team[i] instanceof Programmer){
                numOfPro++;
            }
        }

        if(p instanceof Architect){
            if(numOfArch >= 1){
                throw new TeamException("团队中至多只有一名架构师");
            }
        }else if(p instanceof Designer){
            if(numOfDes >= 2){
                throw new TeamException("团队中至多只有两名设计师");
            }
        }else if(p instanceof Programmer){
            if(numOfPro >= 3){
                throw new TeamException("团队中至多只有三名程序员");
            }
        }

        // 将e添加到现有的team中
        team[total++] = p;

        //p的属性赋值，status、memberid
        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);
    }

    private boolean isExist(Employee e) {
        for(int i = 0; i < total; i++){
            if(e.getId() == this.team[i].getId()){
                return true;
            }
        }
        return false;
    }

    public void removeMember(int memberId){

    }

}
