package de.cuelex.network;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.LoggerType;
import de.cuelex.main.TavaniaCloud;
import de.cuelex.user.client.TavaniaClient;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.network
    Date: 29.05.2021
    
*/
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {


    /*
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.channel().writeAndFlush("Welcome client!");
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, "Connection to client is ready!");
        //PlanetCloud.getInstance().getTavaniaThread().startThread(new FileServerThread(), "FileServerThread");
    }
     */
    public static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private String clientMessage;

    @Override
    public void channelRead0(ChannelHandlerContext channelHandlerContext, String message) throws Exception {
        System.out.println("Packet received: " + message);
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, NettyServerHandler.class, "Received packet: " + message);
        Channel incoming = channelHandlerContext.channel();
        for (Channel channel : channels) {
            if (channel != incoming) {
                channel.writeAndFlush("[" + incoming.remoteAddress() + "] " + message + "\n");
            }
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " has connected!\n");
        }
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, NettyServerHandler.class, incoming.remoteAddress() + " has connected!");
        channels.add(ctx.channel());
        new TavaniaClient(TavaniaClient.getClients().size(), incoming.localAddress().toString(), "TestClient", "Germany", TavaniaCloud.getInstance().getGregorianDate().toString());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " has disconnected!\n");
        }
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, NettyServerHandler.class, incoming.remoteAddress() + " has disconnected!");
        TavaniaClient tavaniaClient = TavaniaClient.clientIpAddress.get(incoming.localAddress().toString());
        TavaniaClient.clients.remove(tavaniaClient);
        TavaniaClient.clientId.remove(tavaniaClient.getId());
        TavaniaClient.clientIpAddress.remove(tavaniaClient.getIpAddress());
        TavaniaCloud.getInstance().getMySQLClientManager().delete(tavaniaClient.getId());
        channels.remove(ctx.channel());
        ctx.close();
    }

    public String getClientMessage() {
        return clientMessage;
    }

    public void setClientMessage(String clientMessage) {
        this.clientMessage = clientMessage;
    }

}
