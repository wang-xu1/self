package design.proxy;

import java.util.Objects;

/**
 * Created by xu on 2020/4/6
 */
public class DBStaticQueryProxy implements IDBQuery {

    DBQuery dbQuery = null;

    @Override
    public String request() {

        if (Objects.isNull(dbQuery)) {
            dbQuery = new DBQuery();
        }
        return dbQuery.request();
    }
}
