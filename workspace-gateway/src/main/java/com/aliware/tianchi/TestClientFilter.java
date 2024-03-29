package com.aliware.tianchi;

import com.aliware.tianchi.base.ServersStatus;
import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;

/**
 * @author daofeng.xjf
 *
 * 客户端过滤器
 * 可选接口
 * 用户可以在客户端拦截请求和响应,捕获 rpc 调用时产生、服务端返回的已知异常。
 */
@Activate(group = Constants.CONSUMER)
public class TestClientFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try{
            ServersStatus.startRequest(invoker.getUrl().toIdentityString());
            invocation.getAttachments().put("startTime",String.valueOf(System.currentTimeMillis()));
            Result result = invoker.invoke(invocation);
            return result;
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public Result onResponse(Result result, Invoker<?> invoker, Invocation invocation) {
        boolean ifSuccess;
        if (result.hasException()||result.getValue() == null||result.getValue().equals("")){
            ifSuccess=false;
        }else {
            ifSuccess=true;
        }
        Long lastTime = System.currentTimeMillis()-Long.parseLong(invocation.getAttachments().get("startTime"));
        ServersStatus.endRequest(invoker.getUrl().toIdentityString(),ifSuccess,lastTime);
        return result;
    }

}
