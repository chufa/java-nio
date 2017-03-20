package com.nio.mina.sendmessage;

import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoHandler;
import org.apache.mina.common.IoSession;

public class sendmessageServerHandler implements IoHandler {


	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		session.write("服务端发送异常..."+cause);
	}


	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		phoneMessage phoneMes = (phoneMessage)message;
        //String[]megs=phoneMes.split(";");
        String sendPhone = phoneMes.getSendPhone();//megs[0];
        String receivePhone = phoneMes.getRecvPhone();//megs[1];
        String mes = phoneMes.getMessage();//megs[2];
        System.out.println("发送人手机号码：" + sendPhone);
        System.out.println("接受人手机号码：" + receivePhone);
        System.out.println("发送信息：" + mes);
        // 短信信息存入移动服务端数据库或者写入手机短信转发队列
        // ...........
        session.write("发送成功！"); // 告诉手机发送信息成功啦
	}


	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		session.close();
	}


	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub

	}

	public void sessionCreated(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}


	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
		// TODO Auto-generated method stub

	}


	public void sessionOpened(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
