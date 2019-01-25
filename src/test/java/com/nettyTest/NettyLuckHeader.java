package com.nettyTest;

import cn.jpush.api.push.model.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyLuckHeader extends SimpleChannelInboundHandler<Message>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
		System.out.println(msg.toString());
	}

	
}
