package com.nio.mina;

import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoFilter;
import org.apache.mina.common.IoFilterChain;
import org.apache.mina.common.IoSession;
import org.apache.mina.common.TrafficMask;
import org.apache.mina.common.WriteRequest;

public class MyIoFilter implements IoFilter{
	public void destroy()throws Exception{
		System.out.println("MyIoFilter destory()...");
	}


	public void exceptionCaught(NextFilter nextFilter, IoSession session, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyIoFilter exceptionCaught()...");
		nextFilter.exceptionCaught(session, cause);
	}


	public void filterClose(NextFilter nextFilter, IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyIoFilter filterClose()...");
		nextFilter.filterClose(session);
	}

	public void filterSetTrafficMask(NextFilter nextFilter, IoSession session,
			TrafficMask arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyIoFilter filterSetTrafficMask()...");
	}


	public void filterWrite(NextFilter nextFilter, IoSession session, WriteRequest arg2)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyIoFilter filterSetTrafficMask()...");
	}


	public void init() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyIoFilter init()...");
	}


	public void messageReceived(NextFilter nextFilter, IoSession session, Object message)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyIoFilter messageReceived()...");
		nextFilter.messageReceived(session, message);
	}


	public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyIoFilter messageSent()...");
		nextFilter.messageSent(session, writeRequest);
	}


	public void onPostAdd(IoFilterChain nextFilter, String session, NextFilter arg2)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyIoFilter onPostAdd()...");
	}


	public void onPostRemove(IoFilterChain nextFilter, String session, NextFilter arg2)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyIoFilter onPostRemove()...");
	}


	public void onPreAdd(IoFilterChain nextFilter, String session, NextFilter arg2)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyIoFilter onPreAdd()...");
	}


	public void onPreRemove(IoFilterChain nextFilter, String session, NextFilter arg2)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyIoFilter onPreRemove()...");
	}


	public void sessionClosed(NextFilter nextFilter, IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyIoFilter sessionClosed()...");
		nextFilter.sessionClosed(session);
	}


	public void sessionCreated(NextFilter nextFilter, IoSession session)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyIoFilter sessionCreated()...");
		nextFilter.sessionCreated(session);
	}


	public void sessionIdle(NextFilter nextFilter, IoSession session, IdleStatus status)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyIoFilter sessionIdle()...");
		nextFilter.sessionIdle(session,status);
	}


	public void sessionOpened(NextFilter nextFilter, IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyIoFilter sessionOpened()...");
		nextFilter.sessionOpened(session);
	}
}
