package com.nio.mina.sendmessage;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.common.IoBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class TextLineCodecEncoder implements ProtocolEncoder{
	private Charset charset; // 编码格式
    private String delimiter; // 文本分隔符
    public TextLineCodecEncoder(Charset charset, String delimiter) {
        this.charset = charset;
        this.delimiter = delimiter;
    }

	public void dispose(IoSession session) throws Exception {
		// TODO Auto-generated method stub
	}


	public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
			throws Exception {
		// TODO Auto-generated method stub
		if (delimiter == null || "".equals(delimiter)) { // 如果文本换行符未指定，使用默认值
            delimiter = "\r\n";
        }
        if (charset == null) {
            charset = Charset.forName("utf-8");
        }
        String value = message.toString();
        IoBuffer buf = IoBuffer.allocate(value.length()).setAutoExpand(true);
        buf.putString(value,charset.newEncoder()); // 真实数据
        buf.putString(delimiter, charset.newEncoder()); // 文本换行符
        buf.flip();
        out.write(buf);
	}
	
}
