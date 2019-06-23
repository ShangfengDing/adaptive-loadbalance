package com.aliware.tianchi;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Server {
    public int maxSuccess = 0;
    public int success = 0;
    public int pending = 0;
    public int error = 0;
    public Long tt = 0L;//延迟总时间，除以成功数是每个成功的延迟时间
    private String url = "";
    private int index;//应该不用了
    public int weight=0;//改为动态生成
    private int initialWeight;//应该就不用了

    public double generateWeight() {
        double errorRate = (1 + success) / (double) (1 + error);
        double delay = (1 + success) / (double) (1 + tt);
        return errorRate * delay;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getInitialWeight() {
        return initialWeight;
    }

    public void setInitialWeight(int initialWeight) {
        this.initialWeight = initialWeight;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getPending() {
        return pending;
    }

    public void setPending(int pending) {
        this.pending = pending;
    }

    public Long getTt() {
        return tt;
    }

    public void setTt(Long tt) {
        this.tt = tt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
