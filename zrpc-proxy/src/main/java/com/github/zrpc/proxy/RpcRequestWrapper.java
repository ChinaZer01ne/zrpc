package com.github.zrpc.proxy;

import com.github.zrpc.protocol.RpcRequest;

import java.lang.reflect.Method;

/**
 * rpc request wrapper
 * @author Zer01ne
 * @since 2020/1/20 0:00
 */
public class RpcRequestWrapper {

    public static RpcRequest wrapper(Method method, Object[] args) {
        RpcRequest request = new RpcRequest();
        request.setServiceName("从注册中心获取");
        request.setHeader("携带参数");
        request.setClassName("ProducerService");
        request.setMethodName("add");
        request.setParameterType(new String[]{"java.lang.Integer", "java.lang.Integer"});
        request.setParameter(new Object[]{1,2});
        return request;
    }
}