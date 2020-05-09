package spring.formework.beans;

/**
 * Created by xu on 2020/4/21
 */
public interface WxBeanFactory {

    /**
     *
     * @param beanName
     * @return
     */
    Object getBean(String beanName);
}
