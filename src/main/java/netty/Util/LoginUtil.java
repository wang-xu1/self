package netty.Util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import netty.attributes.Attributes;

/**
 * Created by xu on 2020-05-09
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }
}
