package com.github.zrpc.serialize;
/**
 * Json serializer
 * @author Zer01ne
 * @since 2020/1/19 22:43
 */
public class JsonSerializer implements Serializer {
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {

        return null;
    }

    public <T> byte[] serialize(T obj) {
        return new byte[0];
    }
}
