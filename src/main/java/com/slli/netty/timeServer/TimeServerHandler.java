package com.slli.netty.timeServer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/13.
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter{
    private int counter;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /*ByteBuf buf= (ByteBuf) msg;
        byte[] req=new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "utf-8");*/
        String body= (String) msg;
        System.out.println("服务端接收到:"+body+"~~~"+(++counter));
        String res=""+new Date()+System.getProperty("line.separator");
        ByteBuf copiedBuffer = Unpooled.copiedBuffer(res.getBytes());
        ctx.write(copiedBuffer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
