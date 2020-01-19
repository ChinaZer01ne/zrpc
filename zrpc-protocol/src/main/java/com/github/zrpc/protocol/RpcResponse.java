package com.github.zrpc.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * rpc response
 * @author Zer01ne
 * @since 2020/1/18 23:17
 */
@Data
public class RpcResponse implements Serializable {
    /**
     * rpc result
     * */
    private Object result;
    /**
     * carry parameter
     * */
    private Object header;
}
