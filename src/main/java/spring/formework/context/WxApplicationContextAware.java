package spring.formework.context;

/**
 * bean的观察器
 */
public interface WxApplicationContextAware {

    void setApplicationContext(WxApplicationContext applicationContext);
}
