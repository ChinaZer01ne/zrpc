package com.github.zrpc.proxy.jdk;

import com.github.zrpc.RpcClient;
import com.github.zrpc.protocol.RpcRequest;
import com.github.zrpc.proxy.RpcRequestWrapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * proxy class handler
 * @author Zer01ne
 * @since 2020/1/20 0:00
 */
public class RpcProxyInvocationHandler implements InvocationHandler {

    private RpcClient client = new RpcClient();
    //private Object target;
    //
    //public RpcProxyInvocationHandler(Object target) {
    //    this.target = target;
    //}

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RpcRequest request = RpcRequestWrapper.wrapper(method, args);
        System.out.println("jdk proxy before");
        client.init();
        client.sendRpcRequest(request);
        //Object result = method.invoke(target, args);
        System.out.println("jdk proxy after");

        Object result = null;
        return result;
    }
}
