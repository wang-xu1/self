package lambda;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by xu on 2020/4/4
 */
public class LambdaDemo {

    public static void main(String[] args) {

        String[] strings = new String[]{"1", "2", "3"};

        /**
         *  filter 按条件过滤数据
         */
        List<String> collect = Stream.of(strings).filter(s -> s.equals("1")).collect(Collectors.toList());

        System.out.println(collect);


        /**
         * map 转换数据
         */
        List<Integer> collect1 = Stream.of(strings).map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        System.out.println(collect1);



    }
}
