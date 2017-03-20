package com.nio.mina.sendmessage;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.logging.Logger;

import org.apache.mina.common.ConnectFuture;
import org.apache.mina.common.IoConnector;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class sendmessageClient {
	private static String hOSTString="127.0.0.1";
	private static int PORT=3005;
	
	public static void main(String [] args){
		IoConnector connector=new NioSocketConnector();
		connector.setConnectTimeout(30000);
		//connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),LineDelimiter.WINDOWS.getValue(),LineDelimiter.WINDOWS.getValue())));
		//connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory()));
		connector.setHandler(new sendmessageClientHandler());
		IoSession session=null;
		try {
			ConnectFuture future=connector.connect(new InetSocketAddress(hOSTString,PORT));
			future.awaitUninterruptibly();
			session = future.getSession();
			String sendPhoneNum="15602982853";
			String recvPhoneNum="10031920270";
			String message="测试发送短信...";
			//String msg=sendPhoneNum+";"+recvPhoneNum+";"+message;
			phoneMessage sendMessage=new phoneMessage();
			sendMessage.setSendPhone(sendPhoneNum);
			sendMessage.setRecvPhone(recvPhoneNum);
			sendMessage.setMessage(message);
			session.write(sendMessage);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("客户端链接异常...");
			e.printStackTrace();
		}
		session.getCloseFuture().awaitUninterruptibly();// 等待连接创建完成
		connector.dispose();
	}
}
