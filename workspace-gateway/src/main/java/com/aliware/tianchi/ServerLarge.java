package com.aliware.tianchi;

import java.util.concurrent.atomic.AtomicInteger;

public class ServerLarge extends Server{

    private final int index = 2;
    private int initialWeight = 300;

    private static ServerLarge instance = new ServerLarge();
    private ServerLarge(){}
    private ServerLarge(AtomicInteger weight){
        this.weight = weight;
    }

    public static ServerLarge getInstance(){
        return instance;
    }

    private AtomicInteger weight = new AtomicInteger(300);//当前权重


}
