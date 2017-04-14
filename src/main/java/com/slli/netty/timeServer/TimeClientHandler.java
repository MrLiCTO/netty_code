package com.slli.netty.timeServer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/13.
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter{
    private final ByteBuf byteBuf;

    public TimeClientHandler() {
        byte[] req=("客户端请求:你好,时间:"+new Date()).getBytes();
        System.out.println(new String(req));
        byteBuf= Unpooled.buffer(req.length);
        byteBuf.writeBytes(req);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf= (ByteBuf) msg;
        byte[] bytes=new byte[((ByteBuf) msg).readableBytes()];
        buf.readBytes(bytes);
        String res = new String(bytes, "utf-8");
        System.out.println(res);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
