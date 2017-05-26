package com.netty.ws;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by wangyong on 2017/5/25.
 */
public class MyWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //http消息编码解码
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());

        //按块方式写的处理器
        pipeline.addLast(new ChunkedWriteHandler());

        //httpcontent聚合成FullHttpRequest，FullHttpResponse
        pipeline.addLast(new HttpObjectAggregator(8192));

        //websocket支持,content-path为ws-demo
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws-demo"));

        pipeline.addLast(new MyWebSocketHandler());
    }
}
