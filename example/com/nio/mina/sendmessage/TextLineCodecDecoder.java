package com.nio.mina.sendmessage;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.common.IoBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class TextLineCodecDecoder implements ProtocolDecoder{
	private Charset charset;  //编码格式
	private String delimiter;	//文本分隔符
	private IoBuffer delimBuf;	//文本分隔符匹配的变量
	private static String CONTEXT=TextLineCodecDecoder.class.getName()+".context";
	
	public TextLineCodecDecoder(Charset charset,String delimiter){
		this.charset=charset;
		this.delimiter=delimiter;
	}
	

	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		// TODO Auto-generated method stub
		Context context=getContext(session);
		if(delimiter==null||"".equals(delimiter)){
			delimiter="\r\n";
		}
		if(charset==null){
			charset=Charset.forName("UTF-8");
		}
		decodeAuto(context,in,out);
	}
	
	private Context getContext(IoSession session){
		Context context=(Context)session.getAttribute(CONTEXT);
		if(context==null){
			context=new Context();
			session.setAttribute(CONTEXT,context);
		}
		return context;
	}
	private void decodeAuto(Context context,IoBuffer in,ProtocolDecoderOutput out) throws CharacterCodingException{
		int matchCount=context.getMatchCount();
		if(delimiter==null){
			IoBuffer tmp=IoBuffer.allocate(2).setAutoExpand(true);
			tmp.putString(delimiter, charset.newEncoder());
			tmp.flip();
			delimBuf=tmp;
		}
		int oldPos=in.position();
		int oldLimit=in.limit();
		
		while(in.hasRemaining()){
			byte b=in.get();
			if(delimBuf.get(matchCount)==b){
				matchCount++;
				if(matchCount==delimBuf.limit()){
					int pos=in.position();
					in.limit(pos);
					in.position(oldPos);
					context.append(in);
					in.limit(oldLimit);
					in.position(pos);
					
					IoBuffer buffer=context.getBuf();
					buffer.flip();
					try {
						out.write(buffer.getString(charset.newDecoder()));
					} catch (Exception e) {
						// TODO: handle exception
					}
					finally{
						buffer.clear();
					}
					
					oldPos=pos;
					matchCount=0;
				}
			}
			else{
				// 如果matchCount==0，则继续匹配
	            // 如果matchCount>0，说明没有匹配到文本换行符的中的前一个匹配成功字节的下一个字节，
				// 跳转到匹配失败字符处，并置matchCount=0，继续匹配
				in.position(in.position()-matchCount);
				matchCount=0;
			}
		}
		in.position(oldPos);
		context.append(in);
		context.setMatchCount(matchCount);
	}


	public void dispose(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		Context context=(Context)session.getAttribute(CONTEXT);
		if(context!=null){
			session.removeAttribute(CONTEXT);
		}
	}

	
	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private class Context{
		private IoBuffer buffer;
		private CharsetDecoder decoder;
		private int matchCount=0;
		public Context(){
			decoder=charset.newDecoder();
			buffer=IoBuffer.allocate(100).setAutoExpand(true);
		}
		public void rest(){
			matchCount=0;
			decoder.reset();
		}
		public void append(IoBuffer in){
			getBuf().put(in);
		}
		public IoBuffer getBuf(){
			return buffer;
		}
		public CharsetDecoder getDecoder(){
			return decoder;
		}
		public int getMatchCount() {
            return matchCount;
        }
        public void setMatchCount(int matchCount) {
            this.matchCount = matchCount;
        }
	}
	
}
