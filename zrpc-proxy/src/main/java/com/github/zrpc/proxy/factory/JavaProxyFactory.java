package com.github.zrpc.proxy.factory;

import com.github.zrpc.proxy.factory.ProxyFactory;
import com.github.zrpc.proxy.jdk.JavaProxyInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * jdk proxy factory
 * @author Zer01ne
 * @since 2020/1/20 0:16
 */
public class JavaProxyFactory implements ProxyFactory {
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object createProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), new JavaProxyInvocationHandler());
    }
}
