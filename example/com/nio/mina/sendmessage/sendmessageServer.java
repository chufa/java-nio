package com.nio.mina.sendmessage;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoAcceptor;
import org.apache.mina.common.IoSessionConfig;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.nio.mina.sendmessage.codec.TextLineCodecDecoder;
import com.nio.mina.sendmessage.codec.TextLineCodecEncoder;
import com.nio.mina.sendmessage.codec.TextLineCodecFactorys;

public class sendmessageServer {
	private static int PORT = 3005;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IoAcceptor acceptor=null;
	        try {
	            // 创建一个非阻塞的server端的Socket
	            acceptor= new NioSocketAcceptor();
	 
	            // 设置过滤器（添加自带的编解码器）
	            acceptor.getFilterChain().addLast(
	                    "codec",
	                    new ProtocolCodecFilter(new TextLineCodecFactorys(
	                            new TextLineCodecDecoder(Charset.forName("utf-8")),
	                            new TextLineCodecEncoder(Charset.forName("utf-8")))));
	            // 设置日志过滤器
	            LoggingFilter lf = new LoggingFilter();
	            lf.setMessageReceivedLogLevel(LogLevel.DEBUG);
	            acceptor.getFilterChain().addLast("logger", lf);
	            // 获得IoSessionConfig对象
	            IoSessionConfig cfg = acceptor.getSessionConfig();
	            // 读写通道10秒内无操作进入空闲状态
	            cfg.setIdleTime(IdleStatus.BOTH_IDLE, 100);
	 
	            // 绑定逻辑处理器
	            acceptor.setHandler(new sendmessageServerHandler());
	            // 绑定端口
	            acceptor.bind(new InetSocketAddress(PORT));
	            System.out.println("服务端启动成功...     端口号为：" + PORT);
	        }catch (Exception e) {
	        	System.out.println("服务端启动异常...."+e);
	            e.printStackTrace();
	        }
	}

}
