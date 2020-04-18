package design.proxy;


/**
 * Created by xu on 2020/4/6
 */
public class DBQuery implements IDBQuery {

    public DBQuery() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String request() {
        return "DBQuery request";
    }
}
