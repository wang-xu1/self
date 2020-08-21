package spring.formework.context;


import spring.formework.beans.WxBeanFactory;
import spring.formework.beans.WxBeanWrapper;
import spring.formework.beans.config.WxBeanDefinition;
import spring.formework.beans.support.WxBeanDefinitionReader;
import spring.formework.beans.support.WxDefaultListableBeanFactory;
import sun.jvm.hotspot.oops.Instance;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xu on 2020/4/21
 */
public class WxApplicationContext extends WxDefaultListableBeanFactory implements WxBeanFactory {


    public String[] configLoactions;

    private WxBeanDefinitionReader reader;

    /**
     * beanDefinitionMap 定义
     */
    private Map<String, WxBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private Map<String, WxBeanWrapper> factoryBeanInstanceCache = new HashMap<>();

    private Map<String, Object> factoryBeanObjectCache = new HashMap<>();


    public WxApplicationContext(String... configLoactions) {

        //定位文件 Reader
        reader = new WxBeanDefinitionReader(this.configLoactions);


        try {

            //加载配置,封装beanDefinition
            List<WxBeanDefinition> wxBeanDefinitions = reader.loadBeanDefinitions();

            //注册beanDefinition到IOC
            doRegisterBeanDefinition(wxBeanDefinitions);

            //注入
            doAutoWrited();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void doAutoWrited() {
        // getBean触发
        for (Map.Entry<String, WxBeanDefinition> beanDefinitionEntry : beanDefinitionMap.entrySet()) {
            String key = beanDefinitionEntry.getKey();
            getBean(key);
        }
    }


    @Override
    public Object getBean(String beanName) {
        //1.获取配置信息
        WxBeanDefinition wxBeanDefinition = this.beanDefinitionMap.get(beanName);
        //2.反射 newInstance()
        Object instance = instantiatieBean(beanName, wxBeanDefinition);

        //3.封装成 beanWrapper
        WxBeanWrapper wxBeanWrapper = new WxBeanWrapper(instance);

        //4.存放到IOC容器
        factoryBeanInstanceCache.put(beanName, wxBeanWrapper);

        //5.DI注入
        populateBean(beanName, wxBeanDefinition, wxBeanWrapper);

        return wxBeanWrapper.getWrapperInstance();
    }


    /**
     * DI注入
     * 循环依赖处理
     *
     * @param beanName
     * @param wxBeanDefinition
     * @param wxBeanWrapper
     */
    private void populateBean(String beanName, WxBeanDefinition wxBeanDefinition, WxBeanWrapper wxBeanWrapper) {
        Object instance = wxBeanWrapper.getWrapperInstance();
        Class<?> clazz = wxBeanWrapper.getWrapperClass();


        for (Field field : clazz.getDeclaredFields()) {

            field.setAccessible(true);


        }


    }


    /**
     * 初始化bean
     *
     * @param beanName
     * @param wxBeanDefinition
     * @return
     */
    private Object instantiatieBean(String beanName, WxBeanDefinition wxBeanDefinition) {
        String className = wxBeanDefinition.getBeanClassName();
        Object instance = null;
        try {
            Class<?> aClass = Class.forName(className);
            instance = aClass.newInstance();
            factoryBeanObjectCache.put(beanName, instance);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return instance;
    }


    public Object getBean(Class clazz) {
        return getBean(clazz.getName());
    }


    /**
     * 注册beanDefinition
     *
     * @param beanDefinitions
     * @throws Exception
     */
    private void doRegisterBeanDefinition(List<WxBeanDefinition> beanDefinitions) throws Exception {
        for (WxBeanDefinition beanDefinition : beanDefinitions) {
            if (this.beanDefinitionMap.containsKey(beanDefinition)) {
                throw new Exception("The " + beanDefinition.getFactoryBeanName() + " is exists");
            }
            beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
    }
}
