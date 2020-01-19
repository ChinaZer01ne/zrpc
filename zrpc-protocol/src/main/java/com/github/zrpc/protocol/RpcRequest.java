package com.github.zrpc.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * rpc request
 * @author Zer01ne
 * @since 2020/1/18 23:17
 */
@Data
public class RpcRequest implements Serializable {
    /**
     * remote service name
     * */
    private String serviceName;
    /**
     * for carrying customized parameter
     * */
    private Object header;
    /**
     * remote call class name
     * */
    private String className;
    /**
     * remote call method name
     * */
    private String methodName;
    /**
     * remote call method parameter type
     * */
    private String[] parameterType;
    /**
     * remote call method parameter
     * */
    private Object[] parameter;

}
