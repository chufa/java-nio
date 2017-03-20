package com.nio.mina.sendmessage;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.common.ConnectFuture;
import org.apache.mina.common.IoConnector;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.nio.mina.sendmessage.codec.TextLineCodecDecoder;
import com.nio.mina.sendmessage.codec.TextLineCodecEncoder;
import com.nio.mina.sendmessage.codec.TextLineCodecFactorys;
import com.nio.mina.sendmessage.message.ChannelInfoRequest;

public class sendmessageClient {
	private static String HOST = "127.0.0.1";
	private static int PORT = 3005;
	
	public static void main(String [] args){
		IoConnector connector=new NioSocketConnector();
		 connector.setConnectTimeout(30000);
	        // 添加过滤器
	        connector.getFilterChain().addLast(
	                "codec",
	                new ProtocolCodecFilter(new TextLineCodecFactorys(
	                        new TextLineCodecDecoder(Charset.forName("utf-8")),
	                        new TextLineCodecEncoder(Charset.forName("utf-8")))));
	        // 添加业务逻辑处理器类
	        connector.setHandler(new sendmessageClientHandler());
	        IoSession session = null;
	        try {
	            ConnectFuture future = connector.connect(new InetSocketAddress(
	                    HOST, PORT));// 创建连接
	            future.awaitUninterruptibly();// 等待连接创建完成
	            session= future.getSession();// 获得session
	            ChannelInfoRequest req = new ChannelInfoRequest(); // 发送请求
	            req.setChannel_id(12345);
	            req.setChannel_desc("mina在做测试哦哦....哇呀呀！！！");
	            session.write(req);// 发送消息
	        }catch (Exception e) {
	        	System.out.println("客户端链接异常..."+e);
	        }
	 
	        session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
	        connector.dispose();
	}
}
