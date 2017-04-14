package com.slli.netty.timeServer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by Administrator on 2017/4/13.
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter{
    private int counter;
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("结束了...");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
            byte[] req=("你好服务端,几点了?"+System.getProperty("line.separator")).getBytes();
            //System.out.println(new String(req));
            ByteBuf byteBuf= Unpooled.buffer(req.length);
            byteBuf.writeBytes(req);
            ctx.writeAndFlush(byteBuf);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /*ByteBuf buf= (ByteBuf) msg;
        byte[] bytes=new byte[((ByteBuf) msg).readableBytes()];
        buf.readBytes(bytes);
        String res = new String(bytes, "utf-8");*/
        String res = (String) msg;
        System.out.println("客户端收到当前时间:"+res+"~~~"+(++counter));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
