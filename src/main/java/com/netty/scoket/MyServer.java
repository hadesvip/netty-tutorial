package com.netty.scoket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 服务器端
 * Created by wangyong on 2017/5/19.
 */
public class MyServer {

    public static void main(String[] args) throws Exception {

        //事件循环组
        // bossGroup负责接受请求并将请求发送给
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            //handler跟childHandler区别
            //handler 是处理bossGroup
            //childHandler是处理workGroup
            serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new MyChannelInitializer());

            //绑定端口,同步等待
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();

            //等待服务器端监听端口关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            //优雅的关闭线程池
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
