package com.shu.team.domain;

public class Architect extends Designer{
    private int stock;

    public Architect() {
    }

    public Architect(int id, String name, int age, double salary, Equipment equipment, double bonus, int stock) {
        super(id, name, age, salary, equipment, bonus);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return getDetails() + "\t\t架构师\t\t" + getStatus() + "\t\t" + getBonus() + "\t\t" + stock + "\t\t" + getEquipment().getDescription();
    }

    public String getDetailsForTeam(){
        return getTeamBaseDetails() + "\t架构师\t" + getBonus() + "\t" + getStock();
    }

}
