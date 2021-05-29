package de.cuelex.network;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.ConsoleLoggerType;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.network
    Date: 29.05.2021
    
*/
public class NettyServerConnection {

    private int port;
    private boolean keepAlive = true;
    private ChannelFuture channelFuture;
    private ChannelHandlerContext channelHandlerContext;

    public void startServer(int port, boolean keepAlive) {
        this.port = port;
        this.keepAlive = keepAlive;
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, NettyServerConnection.class, "Client connected with IP: " + channel.remoteAddress().getHostName());
                    channel.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8),
                            new StringDecoder(StandardCharsets.UTF_8),
                            new NettyServerHandler());
                }
            });
            bootstrap.option(ChannelOption.SO_BACKLOG, 50);
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, isKeepAlive());
            channelFuture = bootstrap.bind(getPort()).sync();
            ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, NettyServerConnection.class, "Netty-Server is listening...");
            channelFuture.channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public ChannelFuture getChannelFuture() {
        return channelFuture;
    }

    public ChannelHandlerContext getChannelHandlerContext() {
        return channelHandlerContext;
    }

    public void setChannel(ChannelHandlerContext channelHandlerContext) {
        this.channelHandlerContext = channelHandlerContext;
    }

}
