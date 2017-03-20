package com.nio.mina.sendmessage;

import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;

import com.nio.mina.sendmessage.message.ChannelInfoRequest;
import com.nio.mina.sendmessage.message.ChannelInfoResponse;
import com.nio.mina.sendmessage.message.EventDto;

public class sendmessageServerHandler extends IoHandlerAdapter {
	@Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("服务端与客户端创建连接...");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("服务端与客户端连接打开...");
    }

    @Override
    public void messageReceived(IoSession session, Object message)
             throws Exception {
        if (message instanceof ChannelInfoRequest) {
             ChannelInfoRequest req = (ChannelInfoRequest) message;
             int channel_id = req.getChannel_id();
             String channel_desc = req.getChannel_desc();
             System.out.println("服务端接收到的数据为：channel_id=" + channel_id
                      +"   channel_desc=" + channel_desc);
             // ================具体操作，比如查询数据库等，这里略....=============
             ChannelInfoResponse res = new ChannelInfoResponse();
             res.setChannelName("CCTV1高清频道");
             EventDto[]events = new EventDto[2];
             for (int i = 0; i < events.length; i++) {
                  EventDto edt = new EventDto();
                  edt.setBeginTime(10);
                  edt.setDayIndex(1);
                  edt.setEventName("风云第一的" + i);
                  edt.setStatus(1);
                  edt.setTotalTime(100+ i);
                  edt.setUrl("www.baidu.com");
                  events[i]= edt;
             }
             res.setEvents(events);
             session.write(res);
        }else {
             System.out.println("未知请求！");
        }

    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        session.close();
        System.out.println("服务端发送信息成功...");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {

    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
             throws Exception {
        System.out.println("服务端进入空闲状态...");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
             throws Exception {
    	System.out.println("服务端发送异常..."+cause);
    }

}
