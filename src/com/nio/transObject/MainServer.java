package com.nio.transObject;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.common.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MainServer {

	private static MainServer mainServer=null;
	private SocketAcceptor acceptor=new NioSocketAcceptor();
	private DefaultIoFilterChainBuilder chain=acceptor.getFilterChain();
	private int bindPort=8080;
	
	public static MainServer getInstances(){
		if(null==mainServer)
			mainServer=new MainServer();
		return mainServer;
	}
	
	private MainServer(){
		chain.addLast("myChain", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		acceptor.setHandler(ServerHandler.getInstance());
		try {
			acceptor.bind(new InetSocketAddress(bindPort));
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainServer.getInstances();
	}

}
