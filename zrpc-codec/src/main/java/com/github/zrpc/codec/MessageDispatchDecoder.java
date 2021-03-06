package com.github.zrpc.codec;

import com.github.zrpc.codec.handler.RpcRequestHandler;
import com.github.zrpc.codec.handler.RpcResponseHandler;
import com.github.zrpc.protocol.RpcRequest;
import com.github.zrpc.protocol.RpcResponse;
import com.github.zrpc.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Message dispatch ,divide into RpcRequest and RpcResponse
 *
 * messageType + serialize type + Rpc request length + Rpc request data
 *
 * @author Zer01ne
 * @since 2020/1/20 16:17
 */
public class MessageDispatchDecoder extends ByteToMessageDecoder {

    private static final int MESSAGE_TYPE_LENGTH = 4;
    private static final int SERIALIZE_TYPE_LENGTH = 4;
    private static final int RPC_REQUEST_LENGTH = 4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        // message type receive complete ?
        if (in.readableBytes() < MESSAGE_TYPE_LENGTH) {
            in.resetReaderIndex();
            return;
        }

        // message type read
        int type = in.readInt();

        if (type == 1) {
            ctx.pipeline().remove(RpcRequestEncoder.class);
            ctx.pipeline().remove(RpcResponseDecoder.class);
            ctx.pipeline().remove(RpcResponseHandler.class);
            ctx.pipeline().names().forEach(System.err::println);
        }

        if (type == 2) {
            ctx.pipeline().remove(RpcResponseEncoder.class);
            ctx.pipeline().remove(RpcRequestDecoder.class);
            ctx.pipeline().remove(RpcRequestHandler.class);
            ctx.pipeline().names().forEach(System.err::println);
        }

        // serialize type ?
        if (in.readableBytes() < SERIALIZE_TYPE_LENGTH) {
            in.resetReaderIndex();
            return;
        }

        int serialize = in.readInt();


        ByteBuf buffer = Unpooled.buffer();


        // request length ?
        if (in.readableBytes() < RPC_REQUEST_LENGTH) {
            in.resetReaderIndex();
            return;
        }
        int length = in.readInt();
        if (in.readableBytes() < length) {
            in.resetReaderIndex();
            return;
        }
        byte[] bytes = new byte[length];
        in.readBytes(bytes);

        buffer.writeInt(serialize);
        buffer.writeInt(length);
        buffer.writeBytes(bytes);

        out.add(buffer);

    }
}