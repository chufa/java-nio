package com.nio.testDemo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
	public static void main(String[] args) throws IOException{
		//get project root path  1
		String sRootPath=System.getProperty("user.dir");
        System.out.println(sRootPath);  
        //get project root path  2
        File directory = new File("");
        try{  
            System.out.println(directory.getCanonicalPath());
            System.out.println(directory.getAbsolutePath());
        }catch(Exception e)  
        {  
            e.printStackTrace();  
        }  
        
		RandomAccessFile accessFile=new RandomAccessFile(sRootPath+ "/src/com/nio/files/FileChannel.d", "rw");
		FileChannel inChannel=accessFile.getChannel();
		ByteBuffer buffer=ByteBuffer.allocate(48);
		//从channel通道读到buffer，对于buffer来说是写的过程
		int bytesRead=inChannel.read(buffer); 
		while(bytesRead!=-1){
			System.out.println("Read "+bytesRead+" byte");
			//buffer 从写转变为读
			buffer.flip();
			while(buffer.hasRemaining()){
				//从buffer读数据
				byte aByte =buffer.get();
				System.out.print((char)aByte);
			}
			buffer.clear();
			bytesRead=inChannel.read(buffer);
		}
		accessFile.close();
	}
}
