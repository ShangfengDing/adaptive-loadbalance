package com.aliware.tianchi;

import java.util.concurrent.atomic.AtomicInteger;

public class ServerLarge extends Server{

    private static ServerLarge instance = new ServerLarge( new AtomicInteger(600));
    private ServerLarge(){}
    private ServerLarge(AtomicInteger weight){
        this.weight = weight;
        this.initialWeight = weight.get();
        this.index = 2;
    }

    public static ServerLarge getInstance(){
        return instance;
    }


}
