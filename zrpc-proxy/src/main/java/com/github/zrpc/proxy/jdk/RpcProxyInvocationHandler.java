package com.github.zrpc.proxy.jdk;

import com.github.zrpc.RpcClient;
import com.github.zrpc.protocol.RpcRequest;
import com.github.zrpc.protocol.RpcResponse;
import com.github.zrpc.proxy.RpcRequestWrapper;
import com.github.zrpc.codec.handler.RpcResultCollector;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.SynchronousQueue;

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
        SynchronousQueue<RpcResponse> responseQueue = new SynchronousQueue<>();
        RpcResultCollector.getRpcResponseMap().put("1", responseQueue);
        client.sendRpcRequest(request);
        RpcResponse take = responseQueue.take();
        System.out.println("jdk proxy after");
        return take.getResult();
    }
}
