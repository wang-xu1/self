package design.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by xu on 2020/4/6
 */
public class main {

    public static void main(String[] args) {

        /**
         * 静态代理
         */
        IDBQuery dbStaticQueryProxy = new DBStaticQueryProxy();
        System.out.println(dbStaticQueryProxy.request());


        /**
         * JDK代理
         */

        IDBQuery o = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{IDBQuery.class}, new JDKDBQueryProxyHandler());
        System.out.println(o.request());



    }

}
