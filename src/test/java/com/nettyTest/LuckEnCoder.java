package com.nettyTest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class LuckEnCoder extends MessageToByteEncoder<LuckMessage>{

	@Override
	protected void encode(ChannelHandlerContext ctx, LuckMessage message, ByteBuf out) throws Exception {
		
		LuckHeader header=message.getLuckHeader();
		
		out.writeInt(header.getVersion());
		out.writeInt(message.getContent().length());
		out.writeBytes(header.getSessionId().getBytes());
		
		out.writeBytes(message.getContent().getBytes());
	}

	
}
