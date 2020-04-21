package formework.beans.support;


import formework.beans.config.WxBeanDefinition;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class WxBeanDefinitionReader {

    private Properties config = new Properties();

    private final String SCAN_PACKAGE = "scanPackage";

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
    }

    public List<WxBeanDefinition> loadBeanDefinitions() {
        return null;
    }
}
