package com.github.zrpc.serialize;
/**
 * @author Zer01ne
 * @since 2020/1/19 22:39
 */
public interface Serializer {
    /**
     * deserialize
     * @param bytes :
     * @param clazz :
     * @return java.lang.Object
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz);
    /**
     * serialize
     * @param obj :
     * @return byte[]
     */
    <T> byte[] serialize(T obj);
}
