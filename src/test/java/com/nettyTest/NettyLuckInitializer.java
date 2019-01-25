package com.nettyTest;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class NettyLuckInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		
		final LuckEnCoder ENCODER =new LuckEnCoder();
		
		ChannelPipeline pipeline = channel.pipeline();
		
		pipeline.addLast(ENCODER);
		pipeline.addLast(new LuckDeCoder());
		
		pipeline.addLast(new NettyLuckHeader());
	}

	
}
