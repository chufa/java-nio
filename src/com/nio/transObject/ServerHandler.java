package com.nio.transObject;

import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoFilterAdapter;
import org.apache.mina.common.IoHandler;
import org.apache.mina.common.IoSession;

/*
 *  sessionCreated...
	sessionOpened...
	messageSent...
	messageReceived...
	messageSent...
	exceptionCaught...
	sessionClosed...
 * */

public class ServerHandler extends IoFilterAdapter implements IoHandler {
	private static ServerHandler sampleMainServerHandler = null;

	public static ServerHandler getInstance() {
		if (null == sampleMainServerHandler)
			sampleMainServerHandler = new ServerHandler();
		return sampleMainServerHandler;
	}

	private ServerHandler() {

	}

	// 当连接后打开时触发此方法，一般此方法与 sessionCreated 会被同时触发
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("sessionOpened..."); 
	}

	//连接关闭时触发
	public void sessionClosed(IoSession session) {
		System.out.println("sessionClosed..."); 
	}

	//当接收到消息时触发
	public void messageReceived(IoSession session, Object message)throws Exception {
		if (message instanceof UserInfo) {
			UserInfo text = (UserInfo) message;
			System.out.println("服务器接收到从客户端的姓名：" + text.getName());
			System.out.println("服务器接收到从客户端的QQ：" + text.getQQNum());
		}
		session.write("this is server send!");
	}

	//当通信异常时触发
	public void exceptionCaught(IoSession session, Throwable arg1)
			throws Exception {
		System.out.println("exceptionCaught..."); 
	}

	// 当消息传送到客户端后触发
	public void messageSent(IoSession session, Object message) throws Exception {
		session.close();
		System.out.println("messageSent..."); 
	}

	// 当一个新客户端连接后触发此方法.
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("sessionCreated..."); 
	}

	// 当连接空闲时触发此方法.
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("sessionIdle..."); 
	}

}
