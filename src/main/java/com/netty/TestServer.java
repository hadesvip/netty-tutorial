package com.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by wangyong on 2017/5/13.
 */
public class TestServer {


    public static void main(String[] args) throws Exception {

        //事件循环组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {

            //服务端启动
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());

            //绑定端口,同步等待
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();

            //等待服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();

        } finally {

            //释放线程池资源
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
