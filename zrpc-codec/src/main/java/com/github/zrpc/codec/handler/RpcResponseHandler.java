package com.github.zrpc.codec.handler;

import com.github.zrpc.protocol.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * rpc response handler
 * @author Zer01ne
 * @since 2020/1/18 23:17
 */
public class RpcResponseHandler extends SimpleChannelInboundHandler<RpcResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse msg) throws Exception {


    }
}