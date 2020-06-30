package netty.protocol.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import netty.protocol.Packet;

import static netty.protocol.command.Command.LOGIN_REQUEST;


/**
 * Created by xu on 2020-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginRequestPacket extends Packet {


    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
