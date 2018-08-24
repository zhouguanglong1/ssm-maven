package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author zhougl
 * 2018/8/9
 **/
public class StreamTest5 {
    public static void main(String[] args) {
        List<Integer> lists = new ArrayList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);
        //并行流的处理方式
        final Integer reduce = lists.parallelStream().reduce(1, (a, b) -> a * (b * 2), (a, b) -> a * b);
        System.out.println(reduce);

        //映射来处理
        final Stream<Integer> integerStream = lists.parallelStream().map(a -> (a * 2));
        final Integer reduce1 = integerStream.reduce(1, (a, b) -> a * b);
        System.out.println(reduce1);

        System.out.println(lists);
    }
}
