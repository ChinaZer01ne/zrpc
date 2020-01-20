package com.github.zrpc;

import com.github.zrpc.initializer.RpcClientInitializer;
import com.github.zrpc.protocol.RpcRequest;
import com.github.zrpc.protocol.RpcResponse;
import com.sun.deploy.config.ClientConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * rpc client
 * @author Zer01ne
 * @since 2020/1/18 23:13
 */
public class RpcClient {
    /**
     * hold current channel
     * */
    private Channel channel;
    /**
     * client start
     */
    public void init() {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new RpcClientInitializer());

        try {
            ChannelFuture future = bootstrap.connect("127.0.0.1", 8888).sync();
            channel = future.channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }
    /**
     * send wrapper request
     * @param request
     * */
    public void sendRpcRequest(RpcRequest request) {
        channel.writeAndFlush(request);
    }

}
