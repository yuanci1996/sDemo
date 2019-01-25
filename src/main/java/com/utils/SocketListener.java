package com.utils;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.netty.WebSocketServer;


public class SocketListener implements ServletContextListener {
	
	private WebSocketServer webSocketServer = null;
	
	public static ExecutorService exec = Executors.newCachedThreadPool();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		webSocketServer.closeSocket();
		exec.shutdownNow();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		webSocketServer = new WebSocketServer(8081);
//		webSocketServer.run();
		exec.execute(webSocketServer);
	}

}
