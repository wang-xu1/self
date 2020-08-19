package spring.formework.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xu on 2020/8/19
 */
@Data

public class WxBeanWrapper {

    private Object wrapperInstance;

    private Class<?> wrapperClass;

    public WxBeanWrapper(Object instance) {
        this.wrapperInstance = instance;
        this.wrapperClass = instance.getClass();
    }
}
