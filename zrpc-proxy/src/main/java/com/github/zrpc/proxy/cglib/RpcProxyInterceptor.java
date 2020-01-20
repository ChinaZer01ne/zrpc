package com.github.zrpc.proxy.cglib;

import com.github.zrpc.RpcClient;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib generate proxy
 * @author Zer01ne
 * @since 2020/1/20 0:07
 */
public class RpcProxyInterceptor implements MethodInterceptor {

    private RpcClient client = new RpcClient();



    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("cglib proxy before");
        client.init();
        //Object result = method.invoke(target, objects);
        Object result = null;
        System.out.println("cglib proxy after");


        return result;
    }
}
