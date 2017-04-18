package com.slli.netty.timeServer;


import com.slli.netty.codeSeria.marshalling.MashallingCodeFactory;
import com.slli.netty.echoServer.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by Administrator on 2017/4/13.
 */
public class TimeClient {
    public void connect(int port,String ip) throws Exception{
        EventLoopGroup workLoopGroup=new NioEventLoopGroup();
        try{
            Bootstrap client = new Bootstrap();
            client.group(workLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            socketChannel.pipeline().addLast(MashallingCodeFactory.buildDecoder());
                            socketChannel.pipeline().addLast(MashallingCodeFactory.buildEncoder());

                            /*socketChannel.pipeline().addLast("frameDecoder",new LengthFieldBasedFrameDecoder(65535,0,2,0,2));

                            socketChannel.pipeline().addLast("msPackDecoder",new MsPackDecoder());

                            socketChannel.pipeline().addLast("frameEncoder",new LengthFieldPrepender(2));

                            socketChannel.pipeline().addLast("msPackEncoder",new MsPackEncoder());*/ //msgPack 编解码

                            /*socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(128));*/ //定长解码

                            /*ByteBuf delimiter= Unpooled.wrappedBuffer("_$".getBytes());
                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));*/ //特殊符号解码

                            /*socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));*/ //换行解码

                            /*socketChannel.pipeline().addLast(new StringDecoder());*/  //字符串解码

                            //socketChannel.pipeline().addLast(new TimeClientHandler());
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture channelFuture = client.connect(ip, port).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            workLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new TimeClient().connect(6666,"127.0.0.1");
    }
}
