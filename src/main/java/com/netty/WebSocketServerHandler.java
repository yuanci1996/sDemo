package com.netty;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

public class WebSocketServerHandler extends ChannelInboundHandlerAdapter {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//存放所有的ChannelHandlerContext
    public static Map<String, ChannelHandlerContext> pushCtxMap = new ConcurrentHashMap<String, ChannelHandlerContext>() ;
    
    //存放某一类的channel
    public static ChannelGroup aaChannelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    
    private WebSocketServerHandshaker handshaker;

    //推送单个
    public static final void push(final ChannelHandlerContext ctx,final String message){
        
        TextWebSocketFrame tws = new TextWebSocketFrame(message);
        ctx.channel().writeAndFlush(tws);
        
    }
    //群发
    public static final void push(final ChannelGroup ctxGroup,final String message){
        
        TextWebSocketFrame tws = new TextWebSocketFrame(message);
        ctxGroup.writeAndFlush(tws);
        
    }
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		 push(ctx, "连接成功");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		for(String key: pushCtxMap.keySet()) {
			if(ctx.equals(pushCtxMap.get(key))) {
				//从连接池剔除
				logger.info("map: {}"+pushCtxMap.size());
//				System.out.println(pushCtxMap.size());
				logger.info("剔除: {}"+key);
//				System.out.println("剔除" +key);
				pushCtxMap.remove(key);
				logger.info("map: {}"+pushCtxMap.size());
			}
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//instanceof是否属于一个类的实例
		if(msg instanceof FullHttpRequest) {
			handleHttpRequest(ctx,(FullHttpRequest)msg);
		}else if(msg instanceof WebSocketFrame) {
			handlerWebSocketFrame(ctx,(WebSocketFrame)msg);
		}
	}

	
	private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
		//断开连接
		if(msg instanceof CloseWebSocketFrame) {
			handshaker.close(ctx.channel(), (CloseWebSocketFrame)msg.retain());
			return;
		}
		//Ping心跳信息
		if(msg instanceof PingWebSocketFrame) {
			ctx.channel().write(new PongWebSocketFrame(msg.content().retain()));
			return;
		}
		if(!(msg instanceof TextWebSocketFrame)) {
			throw new Exception("仅支持文本格式");
		}
		//客户端发送过来的消息
		String request = ((TextWebSocketFrame)msg).text();
//		System.out.println("服务端收到");
		logger.info("服务端收到: {}"+request);
		
		JSONObject jsonObject = null;
		
		try {
			jsonObject = JSONObject.parseObject(request);
			logger.info("json->object: {}"+jsonObject.toJSONString());
		} catch (Exception e) {
			logger.error("服务端异常: {}"+e);
		}
		
		if (jsonObject == null) {
			return;
		}
		String id = (String) jsonObject.get("id");
        String type = (String) jsonObject.get("type");   
       
        //根据id判断是否登陆或者是否有权限等
        
        if(id!=null && !"".equals("id")  &&  type!=null && !"".equals("type")){
            
            //用户是否有权限
            boolean idAccess = true;  
            //类型是否符合定义
            boolean typeAccess = true; 
            
            if(idAccess && typeAccess){
               logger.info("添加到连接池：{}"+request);
                pushCtxMap.put(request,ctx);
                aaChannelGroup.add(ctx.channel());
            }
        }
	}
	
	private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest msg) {
		//第一次为http
		if(!msg.decoderResult().isSuccess()) {
			sendHttpResponse(ctx,msg,
					new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
			return;
		}
		WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory("ws:/"+ctx.channel()+ "/websocket",null,false);
        handshaker = factory.newHandshaker(msg);
        
        
        if(handshaker == null){
            //不支持
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }else{
            
            handshaker.handshake(ctx.channel(), msg);
        }
	}
	
	@SuppressWarnings("deprecation")
	public static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req,
			DefaultFullHttpResponse res) {
		
		if (res.getStatus().code() != 200) {
			ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(),CharsetUtil.UTF_8);
			res.content().writeBytes(buf);
			buf.release();
		}
		
		ChannelFuture f = ctx.channel().writeAndFlush(res);
		if(!isKeepAlive(req) || res.status().code() !=200) {
			f.addListener(ChannelFutureListener.CLOSE);
		}
	}
	
	private static boolean isKeepAlive(FullHttpRequest req) {
		return false;
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		//输出日志
        cause.printStackTrace();
		ctx.close();
	}


	

}
