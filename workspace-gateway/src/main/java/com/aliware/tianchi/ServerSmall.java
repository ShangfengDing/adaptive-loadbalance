package com.aliware.tianchi;

import java.util.concurrent.atomic.AtomicInteger;

public class ServerSmall extends Server{

    private static ServerSmall instance = new ServerSmall(new AtomicInteger(200));
    private ServerSmall(){}
    private ServerSmall(AtomicInteger weight){
        this.weight = weight;
        this.initialWeight = weight.get();
        this.index = 0;
    }

    public static ServerSmall getInstance(){
        return instance;
    }


}
