package netty.protocol.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import netty.protocol.Packet;

import static netty.protocol.command.Command.LOGIN_RESPONSE;

/**
 * Created by xu on 2020-05-09
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
