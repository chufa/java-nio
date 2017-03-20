package com.nio.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.common.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaTimeClient {
	public static void main(String[] args){
		NioSocketConnector connector=new NioSocketConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		connector.setConnectTimeout(30);
		connector.setHandler(new TimeClientHandler());
		ConnectFuture cf=connector.connect(new InetSocketAddress("127.0.0.1",9123));
		//同步阻塞
		cf.awaitUninterruptibly();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//异步
		/*cf.addListener(new IoFutureListener<ConnectFuture>() {
			public void operationComplete(ConnectFuture future) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				IoSession session=future.getSession();
				System.out.println(session.getId());
				System.out.println("异步try catch...");
				future.getSession().write("hello");
				//future.getSession().write("quit");
				
			}
		});
		System.out.println("异步运行结束!");*/
		cf.getSession().getCloseFuture().awaitUninterruptibly();
		connector.dispose();
	}
}
