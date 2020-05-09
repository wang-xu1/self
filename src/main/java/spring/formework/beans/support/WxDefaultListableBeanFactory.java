package spring.formework.beans.support;


import spring.formework.beans.config.WxBeanDefinition;
import spring.formework.context.support.WxAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xu on 2020/4/21
 */
public class WxDefaultListableBeanFactory extends WxAbstractApplicationContext {

    /** Map of bean definition objects, keyed by bean name. */
    private final Map<String, WxBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

}
