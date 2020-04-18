package design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by xu on 2020/4/6
 */
public class JDKDBQueryProxyHandler implements InvocationHandler {
    IDBQuery idbQuery = null;

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

        if (Objects.isNull(idbQuery)){
            idbQuery = new DBQuery();
        }
        return idbQuery.request();
    }
}
