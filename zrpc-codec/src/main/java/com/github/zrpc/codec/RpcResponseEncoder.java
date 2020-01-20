package com.github.zrpc.codec;

import com.github.zrpc.protocol.RpcRequest;
import com.github.zrpc.protocol.RpcResponse;
import com.github.zrpc.serialize.KryoSerializer;
import com.github.zrpc.serialize.SerializeSelector;
import com.github.zrpc.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * rpc response encoder
 *
 * messageType + serialize type + Rpc response length + Rpc response data
 *
 * @author Zer01ne
 * @since 2020/1/18 23:17
 */
public class RpcResponseEncoder extends MessageToByteEncoder<RpcResponse> {
    @Override
    protected void encode(ChannelHandlerContext ctx, RpcResponse msg, ByteBuf out) throws Exception {

        // This is response
        out.writeInt(2);

        // use KryoSerializer
        Serializer serializer = SerializeSelector.select(2);

        byte[] bytes = serializer.serialize(msg);

        // Rpc response length
        out.writeInt(bytes.length);

        // Rpc response data
        out.writeBytes(bytes);

    }
}
