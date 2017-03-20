package com.nio.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.util.ReferenceCountingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaTimeServer {
	private static final int PORT=9123;
	public static void main(String[] args) throws IOException{
		IoAcceptor acceptor=new NioSocketAcceptor();
		//设置日志工厂过滤器  LoggingFilter日志输出
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		//下面是利用SLF4J方式记录错误日志文件
		/*LoggingFilter lf = new LoggingFilter();    
		lf.setSessionOpenedLogLevel(LogLevel.ERROR);    
		acceptor.getFilterChain().addLast("logger", lf);*/  
		 //设置文本编解码过滤器工厂  ProtocolCodecFilter负责编码
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		//将自定义拦截器注册到acceptor
		acceptor.getFilterChain().addLast("myIoFilter", new ReferenceCountingFilter(new MyIoFilter()));
		
		// 设置读取数据的缓冲区大小
        acceptor.getSessionConfig().setReadBufferSize(2048);
        // 读写通道10秒内无操作进入空闲状态
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        
		acceptor.setHandler(new TimeServerHandler());
		acceptor.setDefaultLocalAddress(new InetSocketAddress(PORT));
		
		acceptor.bind();
	}
}
