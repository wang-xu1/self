package netty.protocol.response;

import netty.protocol.Packet;

import static netty.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * Created by xu on 2020-05-09
 */
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
