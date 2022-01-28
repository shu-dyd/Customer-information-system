package com.shu.team.service;

// 单例设计模式，对于某个类只能存在一个对象实例
public class Status {
    private final String NAME;
    private Status(String name){
        this.NAME = name;
    }
    public static final Status FREE = new Status("FREE");
    public static final Status BUSY = new Status("BUSY");
    public static final Status VOCATION = new Status("VOCATION");

    @Override
    public String toString() {
        return NAME;
    }

    public String getNAME() {
        return NAME;
    }
}
