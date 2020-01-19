package com.github.zrpc.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.github.zrpc.protocol.RpcRequest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * kryo serializer
 * @author Zer01ne
 * @since 2020/1/19 22:43
 */
public class KryoSerializer implements Serializer {
    @SuppressWarnings("all")
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {

        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(clazz, new JavaSerializer());

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Input input = new Input(byteArrayInputStream);

        return (T) kryo.readClassAndObject(input);
    }

    public <T> byte[] serialize(T obj) {

        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(obj.getClass(), new JavaSerializer());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);

        kryo.writeClassAndObject(output, obj);
        output.flush();
        output.close();

        byte[] bytes = byteArrayOutputStream.toByteArray();

        try {
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    public static void main(String[] args) {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName("class");
        rpcRequest.setMethodName("method");
        rpcRequest.setServiceName("service");
        byte[] serialize = new KryoSerializer().serialize(rpcRequest);
        RpcRequest deserialize = new KryoSerializer().deserialize(serialize, RpcRequest.class);
        System.out.println(deserialize);

    }
}
