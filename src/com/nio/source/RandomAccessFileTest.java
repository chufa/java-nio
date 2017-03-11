package com.nio.source;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class RandomAccessFileTest {
	static int length=0x8000000; //128Mb
	static String sFilePath=System.getProperty("user.dir")+"/src/com/nio/files/FileChannel.d";
	
	public void randomAccessFile() throws IOException{
		RandomAccessFile rFile=new RandomAccessFile(sFilePath, "rw");
		for (int i = 0; i < 10; i++) {
			rFile.writeDouble(i*1.44);
		}
		rFile.close();
		rFile=new RandomAccessFile(sFilePath, "rw");
		rFile.seek(5*8);//将指针移动到第五个double数据口面
		//覆盖第六位位置的数据
		rFile.writeDouble(47.0001);
		rFile.close();
		rFile=new RandomAccessFile(sFilePath, "rw");
		for (int i = 0; i < 10; i++) {
			System.out.println("vale:"+i+":"+rFile.readDouble());
		}
		rFile.close();
	}
	
	
	public void LargeMappedFile() throws IOException{
		RandomAccessFile sFile=new RandomAccessFile(sFilePath, "rw");
		FileChannel fChannel=sFile.getChannel();
		MappedByteBuffer outBuffer=fChannel.map(FileChannel.MapMode.READ_WRITE, 0, length);
		
		for (int i = 0; i < length; i++) {
			outBuffer.put((byte)'x');
		}
		System.out.println("Finished writeing!");
		
		for (int i = length/2; i < length/2; i++) {
			System.out.println((char)outBuffer.get(i));
		}
		fChannel.close();
	}
}
