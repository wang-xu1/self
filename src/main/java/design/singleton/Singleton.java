package design.singleton;

/**
 * Created by xu on 2020/4/6
 * <p>
 * 静态内部类实现单例模式
 * 1.实现了延迟加载
 * 2.不使用同步关键字
 * 3.反射，和序列化操作会破坏单例
 */
public class Singleton {

    public Singleton() {
        System.out.println("StaticSingleton is create");
    }

    private static class SingletonHolder {
        private static Singleton singleton = new Singleton();
    }


    public static Singleton getInstance() {
        return SingletonHolder.singleton;
    }

    public static void main(String[] args) {
        Singleton instance = getInstance();
        Singleton instance1 = getInstance();
        System.out.println(instance == instance1);
    }


}
