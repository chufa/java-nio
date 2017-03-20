package com.nio.mina.sendmessage;

import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoHandler;
import org.apache.mina.common.IoSession;

public class sendmessageClientHandler implements IoHandler {


	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("客户端发生异常...");
	}


	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("客户端接收到的信息为："+message.toString());
	}


	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		
	}


	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}


	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}


	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// TODO Auto-generated method stub
		
	}


	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
