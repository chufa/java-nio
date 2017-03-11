package com.nio.source;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class bufferArrayWithChannel {
	static String sRootPath=System.getProperty("user.dir");
	
	public static void inChannelToBufferArray() throws IOException{
		RandomAccessFile accessFile=new RandomAccessFile(sRootPath+ "/src/com/nio/files/FileChannel.d", "rw");
		FileChannel inChannel=accessFile.getChannel();
		ByteBuffer bufferHeader=ByteBuffer.allocate(128);
		ByteBuffer bufferBody=ByteBuffer.allocate(1024);
		ByteBuffer[] bufferArrayBuffers={bufferHeader,bufferBody};
		//将channel的数据写入buffer，先写入bufferHeader，写满之后再往bufferBody写
		inChannel.read(bufferArrayBuffers);
	}
	
	public static void BufferArrayToinChannel() throws IOException{
		RandomAccessFile accessFile=new RandomAccessFile(sRootPath+ "/src/com/nio/files/FileChannel.d", "rw");
		FileChannel inChannel=accessFile.getChannel();
		ByteBuffer bufferHeader=ByteBuffer.allocate(128);
		ByteBuffer bufferBody=ByteBuffer.allocate(1024);
		ByteBuffer[] bufferArrayBuffers={bufferHeader,bufferBody};
		
		//将buffer数据写入channel
		inChannel.write(bufferArrayBuffers);
	}
}
