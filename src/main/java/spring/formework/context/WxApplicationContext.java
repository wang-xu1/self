package spring.formework.context;


import spring.formework.beans.WxBeanFactory;
import spring.formework.beans.config.WxBeanDefinition;
import spring.formework.beans.support.WxBeanDefinitionReader;
import spring.formework.beans.support.WxDefaultListableBeanFactory;

import java.util.List;

/**
 * Created by xu on 2020/4/21
 */
public class WxApplicationContext extends WxDefaultListableBeanFactory implements WxBeanFactory {



    public String[] configLoactions;

    private WxBeanDefinitionReader reader;

    public WxApplicationContext(String... configLoactions) {
        this.configLoactions = configLoactions;
    }

    @Override
    public void refresh() {

        //定位文件 Reader
        reader = new WxBeanDefinitionReader(this.configLoactions);

        //加载配置,封装beanDefinition
        List<WxBeanDefinition> wxBeanDefinitions = reader.loadBeanDefinitions();

        //注册beanDefinition到IOC
        doRegisterBeanDefinition(wxBeanDefinitions);

        //提前初始化不延时的类
        doAutoWrited();

    }

    private void doAutoWrited() {
    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }


    private void doRegisterBeanDefinition(List<WxBeanDefinition> beanDefinitions) {

    }
}
