package com.github.zrpc.proxy.factory;

import com.github.zrpc.proxy.jdk.RpcProxyInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * jdk proxy factory
 * @author Zer01ne
 * @since 2020/1/20 0:16
 */
public class JavaProxyFactory implements ProxyFactory {

    @Override
    public Object createProxy(Class[] interfaces) {
        return this.createProxy(interfaces, new RpcProxyInvocationHandler());
    }

    public Object createProxy(Class[] interfaces, InvocationHandler invocationHandler) {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), interfaces, invocationHandler);
    }
}
