package com.github.zrpc.serialize;

/**
 * @author Zer01ne
 * @since 2020/1/19 22:39
 */
public class SerializeSelector {

    public static Serializer select(Integer type) {
        return new KryoSerializer();
    }
}