package com.nio.mina.sendmessage;

import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;

import com.nio.mina.sendmessage.message.ChannelInfoResponse;
import com.nio.mina.sendmessage.message.EventDto;

public class sendmessageClientHandler extends IoHandlerAdapter {

    @Override
    public void messageReceived(IoSession session, Object message)
             throws Exception {
        if (message instanceof ChannelInfoResponse) {
             ChannelInfoResponse res = (ChannelInfoResponse) message;
             String channelName = res.getChannelName();
             EventDto[]events = res.getEvents();
             System.out.println("客户端接收到的消息为：channelName=" + channelName);
             if(events!=null && events.length>0){
                  for (int i = 0; i < events.length; i++) {
                      EventDto edt = events[i];
                      System.out.println("客户端接收到的消息为：BeginTime=" + edt.getBeginTime());
                      System.out.println("客户端接收到的消息为：DayIndex=" + edt.getDayIndex());
                      System.out.println("客户端接收到的消息为：EventName=" + edt.getEventName());
                      System.out.println("客户端接收到的消息为：Status=" + edt.getStatus());
                      System.out.println("客户端接收到的消息为：TotalTime=" + edt.getTotalTime());
                      System.out.println("客户端接收到的消息为：url=" + edt.getUrl());
                  }
             }
        }else{
        	System.out.println("未知类型！");
        }
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
             throws Exception {
    	System.out.println("客户端发生异常..."+cause);
    }

}
