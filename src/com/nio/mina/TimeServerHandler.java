package com.nio.mina;

import java.sql.Date;

import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;

public class TimeServerHandler extends IoHandlerAdapter{
	public void sessionCreated(IoSession session){
		System.out.println(session.getRemoteAddress().toString());
	}
	public void messageReceived(IoSession session,Object message)throws Exception{
		String str=message.toString();
		if(str.trim().equalsIgnoreCase("quit")){
			session.close();
			return;
		}
		Date date=new Date(0);
		session.write(date.toString());
		System.out.println("Message written...");
		System.out.println(str);
	}
	// 当连接空闲时触发此方法.
	@Override
	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("sessionIdle()..."); 
	}
	@Override
	public void messageSent(IoSession session,Object message) throws Exception {
		session.close();
		System.out.println("messageSent()...");
	}
}
