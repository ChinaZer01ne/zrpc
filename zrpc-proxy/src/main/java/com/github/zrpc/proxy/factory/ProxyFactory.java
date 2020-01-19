package com.github.zrpc.proxy.factory;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * client proxy factory
 * @author Zer01ne
 * @since 2020/1/19 23:52
 */
public interface ProxyFactory {

    Object createProxy();

}
