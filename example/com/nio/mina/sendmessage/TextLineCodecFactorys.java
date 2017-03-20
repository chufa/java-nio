package com.nio.mina.sendmessage;

import java.nio.charset.Charset;

import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class TextLineCodecFactorys implements ProtocolCodecFactory{
	private Charset charset; // 编码格式
	private String delimiter; // 文本分隔符

	public TextLineCodecFactorys(Charset charset, String delimiter) {
		this.charset = charset;
		this.delimiter = delimiter;
	}


	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		return new TextLineCodecDecoder(charset,delimiter);
	}


	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		return new TextLineCodecEncoder(charset,delimiter);
	}
	
}
