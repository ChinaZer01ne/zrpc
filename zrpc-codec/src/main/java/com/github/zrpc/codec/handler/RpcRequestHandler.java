package com.github.zrpc.codec.handler;

import com.github.zrpc.container.mock.ServiceContainer;
import com.github.zrpc.protocol.RpcRequest;
import com.github.zrpc.protocol.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * rpc request handler
 * @author Zer01ne
 * @since 2020/1/18 23:17
 */
public class RpcRequestHandler extends SimpleChannelInboundHandler<RpcRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest msg) throws Exception {

        // call method
        Object header = msg.getHeader();
        String className = msg.getClassName();
        String methodName = msg.getMethodName();
        Object[] parameter = msg.getParameter();
        String[] parameterType = msg.getParameterType();
        String serviceName = msg.getServiceName();
        // 增加线程池，异步处理
        Map<String, Object> service = ServiceContainer.getSimpleServiceMap();
        Object o = service.get(className);
        List<Class<?>> collect = Arrays.asList(parameterType).stream().map(s -> {
            try {
                return this.getClass().getClassLoader().loadClass(s);
                //return Class.forName(s);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return Object.class;
        }).collect(Collectors.toList());
        Method declaredMethod = o.getClass().getDeclaredMethod(methodName, collect.toArray(new Class[0]));
        Object result = declaredMethod.invoke(o, parameter);

        RpcResponse response = new RpcResponse();

        response.setHeader("携带参数");
        response.setResult(result);
        ctx.writeAndFlush(response);
    }
}