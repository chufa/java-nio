package com.nio.source;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelRead {
	public static DatagramChannel createSocketChannel(int port) throws IOException{
		DatagramChannel datagramChannelChannel=DatagramChannel.open();
		datagramChannelChannel.configureBlocking(false);
		datagramChannelChannel.socket().bind(new InetSocketAddress(port));
		return datagramChannelChannel;
	}
	
	public static void sChannelRead(){
		ByteBuffer buffer=ByteBuffer.allocate(1024);
		try {
			buffer.clear();
			DatagramChannel datagramChannelChannel=createSocketChannel(7979); 
	        while(datagramChannelChannel.receive(buffer)==null){   
	            try {  
	                Thread.sleep(10);  
	            } catch (InterruptedException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        datagramChannelChannel.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
