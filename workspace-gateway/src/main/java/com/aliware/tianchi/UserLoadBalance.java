package com.aliware.tianchi;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.LoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.RoundRobinLoadBalance;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author daofeng.xjf
 *
 * 负载均衡扩展接口
 * 必选接口，核心接口
 * 此类可以修改实现，不可以移动类或者修改包名
 * 选手需要基于此类实现自己的负载均衡算法
 */
public class UserLoadBalance implements LoadBalance {

    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
//        int small = invokers.get(0).getUrl().getParameter(Constants.THREADS_KEY, Constants.DEFAULT_THREADS);
//        int medium = invokers.get(1).getUrl().getParameter(Constants.THREADS_KEY, Constants.DEFAULT_THREADS);
//        int large = invokers.get(2).getUrl().getParameter(Constants.THREADS_KEY, Constants.DEFAULT_THREADS);
        int typeCount = count.getAndAdd(1);
        typeCount%=6;
        switch (typeCount){
            case 0:
                return invokers.get(0);
            case 1:
            case 2:
                return invokers.get(1);
            default:
                return invokers.get(2);
        }
//        return invokers.get(ThreadLocalRandom.current().nextInt(invokers.size()));
    }

}
