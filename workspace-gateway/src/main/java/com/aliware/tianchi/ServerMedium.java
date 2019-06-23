package com.aliware.tianchi;

import java.util.concurrent.atomic.AtomicInteger;

public class ServerMedium extends Server{


    private static ServerMedium instance = new ServerMedium(new AtomicInteger(450));
    private ServerMedium(){}
    private ServerMedium(AtomicInteger weight){
        this.weight = weight;
        this.initialWeight = weight.get();
        this.index = 1;
    }

    public static ServerMedium getInstance(){
        return instance;
    }


}
