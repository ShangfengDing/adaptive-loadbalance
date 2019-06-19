package com.aliware.tianchi;

import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    int index;
    AtomicInteger weight;
    int initialWeight;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getWeight() {
        return weight.get();
    }

    public void setWeight(int weight) {
        this.weight = new AtomicInteger(weight);
    }

    public int getInitialWeight() {
        return initialWeight;
    }

    public void setInitialWeight(int initialWeight) {
        this.initialWeight = initialWeight;
    }
}
