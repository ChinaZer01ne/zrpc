package com.github.zrpc.codec;

import com.github.zrpc.protocol.RpcRequest;
import com.github.zrpc.protocol.RpcResponse;
import com.github.zrpc.serialize.SerializeSelector;
import com.github.zrpc.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * rpc response decoder
 * @author Zer01ne
 * @since 2020/1/18 23:17
 */
public class RpcResponseDecoder extends ByteToMessageDecoder {

    private static final int RPC_REQUEST_LENGTH = 4;
    private static final int SERIALIZE_TYPE_LENGTH = 4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        // get serialize type length
        int serializeTypeLength = in.readableBytes();
        if (serializeTypeLength < SERIALIZE_TYPE_LENGTH) {
            in.resetReaderIndex();
            return;
        }

        // serialize type
        int serializeType = in.readInt();

        // rpc request length receive complete ?
        int receiveDataLength = in.readableBytes();
        if (receiveDataLength < RPC_REQUEST_LENGTH) {
            in.resetReaderIndex();
            return;
        }

        // serialize type
        int dataLength = in.readInt();

        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }

        // rpc request read
        byte[] bytes = new byte[dataLength];
        in.readBytes(bytes);

        Serializer serializer = SerializeSelector.select(serializeType);
        RpcResponse rpcResponse = serializer.deserialize(bytes, RpcResponse.class);

        out.add(rpcResponse);
    }
}
