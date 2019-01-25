package com.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketServer implements Runnable{

	private Logger logger = LoggerFactory.getLogger(getClass());
    private EventLoopGroup bossGroup = null;
    private EventLoopGroup workerGroup = null;
	private int port;
	
	public WebSocketServer(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		initWebSocketServer();
	}
    
	public void initWebSocketServer() {
		try {
			bossGroup = new NioEventLoopGroup();
			workerGroup = new NioEventLoopGroup();
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline p = ch.pipeline();
					p.addLast("http-codec", new HttpServerCodec());//将请求和应答消息编码或者解码为HTTP消息
					p.addLast("aggregator", new HttpObjectAggregator(65536));//将HTTP消息的多个部分组合成一条完整的HTTP消息
					p.addLast("http-chunked", new ChunkedWriteHandler());//来向客户端发送HTML5文件，主要用于支持浏览器和服务端进行WebSocket通信
					p.addLast("handler", new WebSocketServerHandler());
				}
			});
			
			Channel ch = bootstrap.bind(port).sync().channel();
			
			logger.info("服务端启动，监听端口为 :{}" + port);
			
			ch.closeFuture().sync();
		} catch (InterruptedException e) {
			logger.error("WebSocketServer异常",e);
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	
	}
	
	public void closeSocket() {
        if(bossGroup!=null){
            bossGroup.shutdownGracefully();
        }
        if(workerGroup!=null){
            workerGroup.shutdownGracefully();
        }
        logger.info("关闭WebSocketServer");
    }
}
