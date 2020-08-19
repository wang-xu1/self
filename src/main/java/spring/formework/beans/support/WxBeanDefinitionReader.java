package spring.formework.beans.support;


import spring.formework.beans.config.WxBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class WxBeanDefinitionReader {

    private Properties config = new Properties();

    private final String SCAN_PACKAGE = "scanPackage";

    private List<String> regitryBeanClasses = new ArrayList<>();

    public WxBeanDefinitionReader(String... location) {
        //通过Url定位到具体文件，然后转换成流
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(location[0].replace("classpath:", ""));
        try {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String properties) {
        URL url = this.getClass().getClassLoader().getResource("/" + SCAN_PACKAGE.replaceAll("\\.", ""));
        File classPath = new File(url.getFile());

        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(SCAN_PACKAGE + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String className = SCAN_PACKAGE + "." + file.getName().replace(".class", "");
                regitryBeanClasses.add(className);
            }
        }


    }

    //加载bean中相关的类
    public List<WxBeanDefinition> loadBeanDefinitions() {
        List<WxBeanDefinition> result = new ArrayList<>();
        try {
            for (String className : regitryBeanClasses) {
                Class<?> beanClass = Class.forName(className);
                // 保存类对应的ClassName(全类名)
                // 保存beanName
                // 1. 默认首字母小写
                result.add(doCreateBeanDefiniton(toLowerFirstCase(beanClass.getSimpleName()), beanClass.getName()));

                // 2. 自定义 ?


                // 3. 接口注入
                for (Class<?> i : beanClass.getInterfaces()) {
                    result.add(doCreateBeanDefiniton(i.getName(), beanClass.getName()));
                }


            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }


    private WxBeanDefinition doCreateBeanDefiniton(String beanName, String beanClassName) {
        WxBeanDefinition wxBeanDefinition = new WxBeanDefinition();
        wxBeanDefinition.setBeanClassName(beanClassName);
        wxBeanDefinition.setFactoryBeanName(beanName);
        return wxBeanDefinition;
    }


    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }


}
