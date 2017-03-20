package com.nio.mina.reverseProtocol;

import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;

public class ReverseProtocolHandler extends IoHandlerAdapter{
	public void exceptionCaught (IoSession session,Throwable cause){
		session.close();
	}
	
	public void messageReceived(IoSession session,Object message){
		String str=message.toString();
		StringBuffer buf=new StringBuffer(str.length());
		for (int i = str.length()-1; i >= 0; i--) {
			buf.append(str.charAt(i));
		}
		System.out.println(buf.toString());
		session.write(buf.toString());
	}
}
