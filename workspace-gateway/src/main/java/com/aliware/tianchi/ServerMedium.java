package com.aliware.tianchi;

import java.util.concurrent.atomic.AtomicInteger;

public class ServerMedium extends Server{

    private final int index = 1;
    private int initialWeight = 200;

    private static ServerMedium instance = new ServerMedium();
    private ServerMedium(){}
    private ServerMedium(AtomicInteger weight){
        this.weight = weight;
    }

    public static ServerMedium getInstance(){
        return instance;
    }

    private AtomicInteger weight = new AtomicInteger(200);

}
