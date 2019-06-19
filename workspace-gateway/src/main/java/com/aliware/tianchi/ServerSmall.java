package com.aliware.tianchi;

import java.util.concurrent.atomic.AtomicInteger;

public class ServerSmall extends Server{

    private final int index = 0;
    private int initialWeight = 100;
    private static ServerSmall instance = new ServerSmall();
    private ServerSmall(){}
    private ServerSmall(AtomicInteger weight){
        this.weight = weight;
    }

    public static ServerSmall getInstance(){
        return instance;
    }

    private AtomicInteger weight = new AtomicInteger(100);

}
