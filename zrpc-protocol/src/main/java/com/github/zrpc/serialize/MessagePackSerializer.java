package com.github.zrpc.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.JavaSerializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * MessagePack serializer
 * @author Zer01ne
 * @since 2020/1/19 22:43
 */
public class MessagePackSerializer implements Serializer {
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {

        return null;
    }

    public <T> byte[] serialize(T obj) {


        return new byte[0];
    }
}
