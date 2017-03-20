package com.nio.mina.reverseProtocol;

import java.io.IOException;
import java.net.InetSocketAddress;
import org.apache.mina.common.IoAcceptor;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class Main {

	/**
	 * @param args
	 */
	private static final int PORT=8080;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		IoAcceptor acceptor = new NioSocketAcceptor();
		// Prepare the configuration
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        //acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
       
        // Bind
        acceptor.setHandler(new ReverseProtocolHandler());
        acceptor.bind(new InetSocketAddress(PORT));

        System.out.println("Listening on port " + PORT);
	}
}
