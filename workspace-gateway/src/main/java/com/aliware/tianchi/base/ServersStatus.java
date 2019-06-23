package com.aliware.tianchi.base;

import com.aliware.tianchi.Server;
import com.aliware.tianchi.UserLoadBalance;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * @Author: fuhua
 * @Date: 2019-06-21 13:00
 */
public class ServersStatus {
    private static final Logger logger = LoggerFactory.getLogger(ServersStatus.class);
    /*
     * 记录Server的状态
     */
//    private static final ConcurrentMap<String, Server> servers = new ConcurrentHashMap();

    public static void startRequest(String url){
        Server server = UserLoadBalance.servers.get(url);
        server.pending++;
    }

    public static void endRequest(String url,boolean ifSuccess,Long lastTime){
        Server server = UserLoadBalance.servers.get(url);
        server.pending--;
        if (ifSuccess){
            server.success++;
            server.tt+=lastTime;
        }else{
            server.error++;
            server.maxSuccess=server.success;
            server.success=0;
        }
    }

}
