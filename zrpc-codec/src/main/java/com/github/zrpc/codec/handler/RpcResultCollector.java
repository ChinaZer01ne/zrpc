package com.github.zrpc.codec.handler;

import com.github.zrpc.protocol.RpcRequest;
import com.github.zrpc.protocol.RpcResponse;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

/**
 * rpc request wrapper
 * @author Zer01ne
 * @since 2020/1/21 0:00
 */
public class RpcResultCollector {

    /**
     * hold response
     * */
    private static Map<String, SynchronousQueue<RpcResponse>> rpcResponseMap = new ConcurrentHashMap<>();

    public static Map<String, SynchronousQueue<RpcResponse>> getRpcResponseMap() {
        return rpcResponseMap;
    }
}