package com.nio.mina.sendmessage;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoAcceptor;
import org.apache.mina.common.IoSessionConfig;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class sendmessageServer {
	private static int PORT=3005;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IoAcceptor acceptor=null;
		try {
			acceptor=new NioSocketAcceptor();
			//acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),LineDelimiter.WINDOWS.getValue(),LineDelimiter.WINDOWS.getValue())));
			//acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
			acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory()));
			IoSessionConfig cfg=acceptor.getSessionConfig();
			cfg.setIdleTime(IdleStatus.BOTH_IDLE,100);
			acceptor.setHandler(new sendmessageServerHandler());
			acceptor.bind(new InetSocketAddress(PORT));
			System.out.println("服务端启动异常...  端口号为：" + PORT);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("服务端启动异常...");
			e.printStackTrace();
		}
	}

}
