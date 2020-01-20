package com.github.zrpc.codec;

import com.github.zrpc.protocol.RpcRequest;
import com.github.zrpc.serialize.KryoSerializer;
import com.github.zrpc.serialize.SerializeSelector;
import com.github.zrpc.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * rpc request encoder
 *
 * messageType + serialize type + Rpc request length + Rpc request data
 *
 * @author Zer01ne
 * @since 2020/1/18 23:17
 */
public class RpcRequestEncoder extends MessageToByteEncoder<RpcRequest> {
    @Override
    protected void encode(ChannelHandlerContext ctx, RpcRequest msg, ByteBuf out) throws Exception {

        // This is a request
        out.writeInt(1);

        // use KryoSerializer
        out.writeInt(1);

        Serializer serializer = SerializeSelector.select(1);
        byte[] bytes = serializer.serialize(msg);

        // Rpc request length
        out.writeInt(bytes.length);

        // Rpc request data
        out.writeBytes(bytes);
    }
}
