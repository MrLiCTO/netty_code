package com.slli.netty.echoServer;

import com.slli.netty.model.ShortMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/13.
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter{
    private int counter;

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("结束了...");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
            ShortMsg shortMsg = new ShortMsg("666" + i,"nv", "echo" + i, i, new Date());
            ctx.writeAndFlush(shortMsg);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ShortMsg shortMsg = (ShortMsg) msg;
        System.out.println(msg+""+(++counter));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
