package com.nio.source;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelWrite {
	public void datagramChannelSend(String hostName,int port) throws IOException{
		DatagramChannel channel =DatagramChannel.open();  
        ByteBuffer buffer =ByteBuffer.wrap("This is datagramChannelServer!".getBytes("utf-8"));  
        channel.send(buffer, new InetSocketAddress(hostName,port));  
          
        channel.close();  
	}
}
