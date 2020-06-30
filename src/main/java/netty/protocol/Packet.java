package netty.protocol;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by xu on 2020-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class Packet {


    /**
     * 协议版本
     */
    private Byte version = 1;


    public abstract Byte getCommand();
}
