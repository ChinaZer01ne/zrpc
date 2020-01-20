package com.github.zrpc.proxy.factory;

import com.github.zrpc.proxy.cglib.RpcProxyInterceptor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Proxy;
/**
 * cglib proxy factory
 * @author Zer01ne
 * @since 2020/1/20 0:16
 */
public class CglibProxyFactory implements ProxyFactory {

    @Override
    public Object createProxy(Class[] target){
        return this.createProxy(target[0], new RpcProxyInterceptor());
    }

    public Object createProxy(Class target, MethodInterceptor methodInterceptor){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target);
        enhancer.setCallback(methodInterceptor);
        return enhancer.create();
    }
}
