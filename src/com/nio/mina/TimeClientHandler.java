package com.nio.mina;

import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;

public class TimeClientHandler extends IoHandlerAdapter{
	public TimeClientHandler(){
		
	}
	public void messageReceived(IoSession session,Object message){
		System.out.println(message.toString());
	}
}
