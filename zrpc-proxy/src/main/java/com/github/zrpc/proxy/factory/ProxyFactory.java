package com.github.zrpc.proxy.factory;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * client proxy factory
 * @author Zer01ne
 * @since 2020/1/19 23:52
 */
public interface ProxyFactory {
    /**
     * create proxy object
     * @param interfaces target object interface
     * @return Java.lang.Object
     * */
    Object createProxy(Class[] interfaces);

}
