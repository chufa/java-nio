package com.nio.source;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.baselib.StringToBytebuffer;

public class SocketChannelRW {
	public static SocketChannel createSocketChannel(String hostname,int port) throws IOException{
		SocketChannel socketChannel=SocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.connect(new InetSocketAddress(hostname,port));
		return socketChannel;
	}
	
	public static void socketChannelCommunite(){
		ByteBuffer recvBuffer=ByteBuffer.allocate(1024);
		StringToBytebuffer stb=new StringToBytebuffer();
		ByteBuffer writeBuffer=stb.StringToByte("hello This SocketChannel send.");
		
		try {
			recvBuffer.clear();
			SocketChannel socketChannel=createSocketChannel("l27.0.0.1", 7979);
			while(!socketChannel.finishConnect()){
				System.out.println("等待非阻塞链接建立...");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			int numberBytesRead;
			while((numberBytesRead=socketChannel.read(recvBuffer))!=-1){
				if(numberBytesRead==0){
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					continue;
				}
				recvBuffer.flip();
				while(recvBuffer.remaining()>0){
					System.out.println((char)recvBuffer.get());
				}
				//字符转换为字符串
				byte[] buff=new byte[1024];
				recvBuffer.get(buff,0,numberBytesRead);
				String sbuffString=new String(buff,0,numberBytesRead,"UTF-8");
				System.out.println(sbuffString);
				recvBuffer.clear();
				
				//socketChannel返回
				socketChannel.write(writeBuffer);
				writeBuffer.clear();
			}
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
