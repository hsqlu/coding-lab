package com.hsqlu.coding.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created: 2016/4/24.
 * Author: Qiannan Lu
 */
public class NettyDemo {
    /*public static void main(String[] args) throws InterruptedException {
        ServerBootstrap server = new ServerBootstrap();

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        server.channel(NioServerSocketChannel.class).group(bossGroup, workGroup);


        server.bind(11211).sync();
    }*/
}
