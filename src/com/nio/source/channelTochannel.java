package com.nio.source;

import java.io.IOException;
import java.nio.channels.FileChannel;

public class channelTochannel {
	public void channelToAnother(FileChannel fromChannel,FileChannel toChannel) throws IOException{
		long positon=0;
		long count=fromChannel.size();
		//transferFrom
		toChannel.transferFrom(fromChannel, positon, count);
		//transferTo 效果与上面一样
		//fromChannel.transferTo(positon, count, toChannel);
	}
}
