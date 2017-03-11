package com.baselib;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class StringToBytebuffer {
	public String ByteToString(ByteBuffer buffer,String charsetString){
		CharBuffer charBuffer=null;
		CharsetDecoder decoder=Charset.forName(charsetString).newDecoder();
		try {
			charBuffer=decoder.decode(buffer.asReadOnlyBuffer());
		} catch (CharacterCodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return charBuffer.toString();
	}
	public ByteBuffer StringToByte(String sbuffer){
		return ByteBuffer.wrap(sbuffer.getBytes());
	}
}
