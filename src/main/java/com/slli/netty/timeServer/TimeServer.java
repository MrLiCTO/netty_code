package com.slli.netty.timeServer;


import com.slli.netty.codeSeria.marshalling.MashallingCodeFactory;
import com.slli.netty.echoServer.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Administrator on 2017/4/13.
 */
public class TimeServer {
    public void bind(int port) throws Exception{
        EventLoopGroup bossLoopGroup=new NioEventLoopGroup();
        EventLoopGroup workLoopGroup=new NioEventLoopGroup();
        try{
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossLoopGroup, workLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 64)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            socketChannel.pipeline().addLast(MashallingCodeFactory.buildDecoder());
                            socketChannel.pipeline().addLast(MashallingCodeFactory.buildEncoder());

                            /*socketChannel.pipeline().addLast("frameDecoder",new LengthFieldBasedFrameDecoder(65535,0,2,0,2));

                            socketChannel.pipeline().addLast("msPackDecoder",new MsPackDecoder());

                            socketChannel.pipeline().addLast("frameEncoder",new LengthFieldPrepender(2));

                            socketChannel.pipeline().addLast("msPackEncoder",new MsPackEncoder());*/  //msgPack编解码

                            /*socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(128));*/ //定长解码

                            /*ByteBuf delimiter= Unpooled.wrappedBuffer("_$".getBytes());
                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));*/ //特殊符号解码

                            System.out.println("服务端连接成功...");

                            /*socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));*/ //换行解码

                            /*socketChannel.pipeline().addLast(new StringDecoder());*/

                            //socketChannel.pipeline().addLast(new TimeServerHandler());
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            ChannelFuture channelFuture = server.bind("127.0.0.1", port).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossLoopGroup.shutdownGracefully();
            workLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new TimeServer().bind(6666);
    }
}
