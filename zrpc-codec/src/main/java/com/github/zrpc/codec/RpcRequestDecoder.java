package com.github.zrpc.codec;

import com.github.zrpc.protocol.RpcRequest;
import com.github.zrpc.serialize.KryoSerializer;
import com.github.zrpc.serialize.SerializeSelector;
import com.github.zrpc.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * rpc request decoder
 *
 * messageType + Rpc request length + Rpc request data
 *
 * @author Zer01ne
 * @since 2020/1/18 23:17
 */
public class RpcRequestDecoder extends ByteToMessageDecoder {

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

        if (in.readableBytes() < serializeTypeLength) {
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

        if (in.readableBytes() < receiveDataLength) {
            in.resetReaderIndex();
            return;
        }

        // rpc request read
        byte[] bytes = new byte[receiveDataLength];
        in.readBytes(bytes);

        Serializer serializer = SerializeSelector.select(serializeType);
        RpcRequest rpcRequest = serializer.deserialize(bytes, RpcRequest.class);

        out.add(rpcRequest);
    }
}
