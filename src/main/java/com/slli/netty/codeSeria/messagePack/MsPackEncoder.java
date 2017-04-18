package com.slli.netty.codeSeria.messagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * Created by Administrator on 2017/4/18.
 */
public class MsPackEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        try{
            MessagePack messagePack = new MessagePack();
            byte[] bytes = messagePack.write(o);
            byteBuf.writeBytes(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
