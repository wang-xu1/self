package netty.handler.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.protocol.request.LoginRequestPacket;
import netty.protocol.PacketCodeC;

import java.util.Date;
import java.util.UUID;

/**
 * Created by xu on 2020-05-09
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + ": 客户端开始登录");

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("wax");
        loginRequestPacket.setPassword("pwd");

        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        ctx.channel().writeAndFlush(buffer);

    }
}
