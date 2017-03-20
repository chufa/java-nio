package com.nio.transObject;

import java.net.InetSocketAddress;

import org.apache.mina.common.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MainClient {
	private static MainClient mainClient = null;
    NioSocketConnector connector = new NioSocketConnector();
    DefaultIoFilterChainBuilder chain = connector.getFilterChain();
    
	public static MainClient getInstances() {
		if (null == mainClient) {
			mainClient = new MainClient();
		}
		return mainClient;

	}

	private MainClient() {
		chain.addLast("myChin", new ProtocolCodecFilter(
		new ObjectSerializationCodecFactory()));
		connector.setHandler(ClientHandler.getInstances());
		connector.setConnectTimeout(30);
		connector.connect(new InetSocketAddress("localhost",8080));
	}
    
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainClient.getInstances();
	}

}
