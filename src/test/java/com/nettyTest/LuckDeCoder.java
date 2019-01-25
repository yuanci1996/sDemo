package com.nettyTest;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class LuckDeCoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		int version=in.readInt();
		int contentMessage=in.readInt();
		byte[] sessionByte = new byte[36];
		in.readBytes(sessionByte);
		String sessionId=new String(sessionByte);
		
		LuckHeader header=new LuckHeader(version,contentMessage,sessionId);
		
		byte[] content=in.readBytes(in.readableBytes()).array();
		
		LuckMessage message=new LuckMessage(header,new String(content));
		
		out.add(message);
	}

	
}
