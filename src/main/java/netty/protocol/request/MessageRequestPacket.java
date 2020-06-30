package netty.protocol.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import netty.protocol.Packet;

import static netty.protocol.command.Command.MESSAGE_REQUEST;

/**
 * Created by xu on 2020-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
