package com.github.zrpc.initializer;

import com.github.zrpc.codec.*;
import com.github.zrpc.codec.handler.RpcRequestHandler;
import com.github.zrpc.codec.handler.RpcResponseHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author Zer01ne
 * @since 2020/1/18 23:25
 */
public class RpcServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new MessageDispatchDecoder());

        pipeline.addLast(new RpcRequestEncoder());
        pipeline.addLast(new RpcRequestDecoder());

        pipeline.addLast(new RpcResponseEncoder());
        pipeline.addLast(new RpcResponseDecoder());

        pipeline.addLast(new RpcRequestHandler());
        pipeline.addLast(new RpcResponseHandler());
    }
}
