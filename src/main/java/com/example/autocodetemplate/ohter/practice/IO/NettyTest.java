package com.example.autocodetemplate.ohter.practice.IO;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class NettyTest {
    public static void main(String[] args) throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run() {
                new NettyServer(9527).start();
            }
        }).start();

        Thread.sleep(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                new NettyClient("192.168.1.120",9527).start();
            }
        }).start();
    }
}

final class NettyServer {
    private final int port;

    NettyServer(int port) {
        this.port = port;
    }

    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .localAddress(this.port)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel channel) throws Exception {
                        System.out.println("connected...; Client:" + channel.remoteAddress());
                        channel.pipeline().addLast(new ServerHandler());
                    }
                });

        try {
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.err.println("netty服务器-》关闭服务通道失败");
        }finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                System.err.println("netty服务器-》释放线程池资源失败");
            }
        }
    }
}

final class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("netty服务器-》server channelRead...; received:" + msg);
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("netty服务器-》server channelReadComplete..");

        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("netty服务器-》server occur exception:" + cause.getMessage());
        cause.printStackTrace();
        ctx.close();
  }
}

final class NettyClient {
    private final String host;
    private final int port;

    NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void  start() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .remoteAddress(new InetSocketAddress(host, port))
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) {
                        System.out.println("netty客户端-》connected ....");
                        channel.pipeline().addLast(new ClientHandler());
                    }
                });
        System.out.println("netty客户端-》create...");


        try {
            ChannelFuture channelFuture = bootstrap.connect().sync();
            System.out.println("netty客户端-》异步连接服务器...");
            channelFuture.channel().closeFuture().sync();
            System.out.println("netty客户端-》异步关闭服务器...");
        } catch (InterruptedException e) {
            System.err.println("netty客户端-》关闭服务通道失败");
        }finally {
            try {
                eventLoopGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                System.err.println("netty客户端-》释放线程池资源失败");
            }
        }
    }
}

final class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        System.out.println("netty客户端-》通道数据读取中...");
        ByteBuf buf = byteBuf.readBytes(byteBuf.readableBytes());
        System.out.println("Client received:" + ByteBufUtil.hexDump(buf) + "; The value is:" + buf.toString(Charset.forName("utf-8")));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("netty客户端-》通道数据活跃中..");
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8)); // 必须有flush
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
