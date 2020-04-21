package formework.beans.config;

import lombok.Data;

/**
 * Created by xu on 2020/4/21
 */

@Data
public class WxBeanDefinition {

    private  String beanClassName;

    private boolean lazyInit = false;

    private String factoryBeanName;
}
