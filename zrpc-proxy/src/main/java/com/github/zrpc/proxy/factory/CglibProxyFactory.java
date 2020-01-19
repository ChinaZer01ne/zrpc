package com.github.zrpc.proxy.factory;

import com.github.zrpc.proxy.cglib.CglibProxyInterceptor;
import com.github.zrpc.proxy.factory.ProxyFactory;
import com.github.zrpc.proxy.jdk.JavaProxyInvocationHandler;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;
/**
 * cglib proxy factory
 * @author Zer01ne
 * @since 2020/1/20 0:16
 */
public class CglibProxyFactory implements ProxyFactory {
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object createProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new CglibProxyInterceptor());
        return enhancer.create();
    }

}
