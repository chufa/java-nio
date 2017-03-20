package com.nio.transObject;

import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;

public class ClientHandler extends IoHandlerAdapter {
	private static ClientHandler samplMinaClientHandler = null;

	public static ClientHandler getInstances() {
		if (null == samplMinaClientHandler) {
			samplMinaClientHandler = new ClientHandler();
		}
		return samplMinaClientHandler;
	}

	private ClientHandler() {
	}

	public void sessionOpened(IoSession session) throws Exception {
		session.write("客户端与服务器的会话打开了……");
		UserInfo text = new UserInfo();
		text.setName("QQ");
		text.setQQNum("1003192027");
		session.write(text);
	}

	public void sessionClosed(IoSession session) {
		System.out.println("sessionClosed...");
		session.close();
	}

	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.println(message.toString());
	}

	public void messageSent(IoSession arg0, Object arg1) throws Exception {
		System.out.println("客户端已经向服务器发送了：" + arg1.toString());
	}

	public void exceptionCaught(IoSession arg0, Throwable arg1)
			throws Exception {
		System.out.println("exceptionCaught...");
	}

	// 当一个新客户端连接后触发此方法.

	public void sessionCreated(IoSession arg0) throws Exception {
		System.out.println("sessionCreated...");
	}

	// 当连接空闲时触发此方法.

	@Override
	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("sessionIdle...");
	}
}
