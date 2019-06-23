package com.aliware.tianchi;

import com.aliware.tianchi.base.ServersStatus;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.LoadBalance;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author daofeng.xjf
 * <p>
 * 负载均衡扩展接口
 * 必选接口，核心接口
 * 此类可以修改实现，不可以移动类或者修改包名
 * 选手需要基于此类实现自己的负载均衡算法
 */
public class UserLoadBalance implements LoadBalance {

    private static int largeWeight = 300;
    private static int mediumWeight = 200;
    private static int smallWeight = 100;

    public static ConcurrentMap<String ,Server> servers = new ConcurrentHashMap();


    //    @Override
//    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
//        int small = invokers.get(0).getUrl().getParameter(Constants.THREADS_KEY, Constants.DEFAULT_THREADS);
//        int medium = invokers.get(1).getUrl().getParameter(Constants.THREADS_KEY, Constants.DEFAULT_THREADS);
//        int large = invokers.get(2).getUrl().getParameter(Constants.THREADS_KEY, Constants.DEFAULT_THREADS);
//        int typeCount = count.getAndAdd(1);
//        typeCount%=6;
//        switch (typeCount){
//            case 0:
//                return invokers.get(0);
//            case 1:
//            case 2:
//                return invokers.get(1);
//            default:
//                return invok
//                ers.get(2);
//        }
////        return invokers.get(ThreadLocalRandom.current().nextInt(invokers.size()));
//    }
    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        if (invokers==null||invokers.isEmpty()){
            return null;
        }
        if (invokers.size()==1){
            return invokers.get(0);
        }
        if (servers.size()==0) {
            synchronized (servers) {
                if (servers.size() == 0) {
                    servers = new ConcurrentHashMap();
                    int count = 0;
                    for (Invoker invoker : invokers) {
                        Server temp =new Server();
                        temp.setIndex(count);
                        servers.put(invoker.getUrl().toIdentityString(), temp);
                        count++;
                    }
                }
            }
        }
        double max = 0;
        int index = -1;
        for (int i=0;i<invokers.size();i++){
            Server server = servers.get(invokers.get(i).getUrl().toIdentityString());
            double weight = server.generateWeight();
            if(weight>max){
                max=weight;
                index=server.getIndex();
            }
            if (weight==0) {
                index=-1;
                break;
            }
        }
        if (index==-1){
            index = ThreadLocalRandom.current().nextInt(invokers.size());
        }
        return invokers.get(index);
//        int totalWeight = serverLarge.getWeight() + serverMedium.getWeight() + serverSmall.getWeight();
//        System.out.println(totalWeight);
//        Server max;
//        int largeWeight = serverLarge.getWeight();
//        int mediumWeight = serverMedium.getWeight();
//        int smallWeight = serverSmall.getWeight();
//        if (largeWeight > mediumWeight) {
//            max = serverLarge;
//        } else {
//            max = serverMedium;
//        }
//        if (largeWeight < smallWeight) {
//            max = serverSmall;
//        }
//        max.setWeight(max.getWeight() - totalWeight);
//        serverSmall.setWeight(serverSmall.getWeight() + serverSmall.getInitialWeight());
//        serverMedium.setWeight(serverMedium.getWeight() + serverMedium.getInitialWeight());
//        serverLarge.setWeight(serverLarge.getWeight() + serverLarge.getInitialWeight());
//        System.out.println(max.index);
//        return invokers.get(max.getIndex());

    }


//    private int maxWeight() {
//        if(mediumWeight>=smallWeight&&mediumWeight>=largeWeight){
//            return mediumWeight;
//        } else if(largeWeight>=mediumWeight&&largeWeight>=smallWeight){
//            return largeWeight;
//        } else {
//            return smallWeight;
//        }
//    }
//
//    /**
//     * 返回所有服务器的权重的最大公约数
//     *
//     * @return
//     */
//    private int serverGcd() {
//        int comDivisor = 0;
//        for (int i = 0; i < 2; i++) {
//            if (comDivisor == 0) {
//                comDivisor = gcd(servers.get(i).getWeight(), servers.get(i + 1).getWeight());
//            } else {
//                comDivisor = gcd(comDivisor, servers.get(i + 1).getWeight());
//            }
//        }
//        return comDivisor;
//    }
//
//    /**
//     * 求两个数的最大公约数
//     *
//     * @param num1
//     * @param num2
//     * @return
//     */
//    private int gcd(int num1, int num2) {
//        BigInteger i1 = new BigInteger(String.valueOf(num1));
//        BigInteger i2 = new BigInteger(String.valueOf(num2));
//        return i1.gcd(i2).intValue();
//    }


}
