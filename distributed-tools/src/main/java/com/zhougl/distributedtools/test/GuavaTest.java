package com.zhougl.distributedtools.test;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.collect.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zhougl
 * 2019/4/5
 **/
public class GuavaTest {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("程序员");
        list.add("周广隆");
        list.add("产品经理");
        list.add("林喵喵");
        list.add("");
        list.add(null);
        // 字符串拼接
        String nameJoiner = Joiner.on(",").skipNulls().join(list);
        System.out.println(nameJoiner);
        // 拆分成集合
        List<String> strings = Splitter.on(",").omitEmptyStrings().splitToList(nameJoiner);
        System.out.println(strings);
        // 求交集并集差集
        HashSet<String> set = Sets.newHashSet("hello","world","shabi");
        HashSet<String> set1 = Sets.newHashSet("hi","zhougl","shabi");
        // 并集
        Sets.SetView<String> union = Sets.union(set, set1);
        // 差集
        Sets.SetView<String> difference = Sets.difference(set, set1);
        // 交集
        Sets.SetView<String> intersection = Sets.intersection(set, set1);
        System.out.println(union);
        System.out.println(difference);
        System.out.println(intersection);

        // 检查异常工具
        double money = -1;
        try {
            Preconditions.checkArgument(money > 0,"必须大于零元:s%",money);
        } catch (Exception e){
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        // multimap 一对多map
        Multimap<String,String> multimap = ArrayListMultimap.create();
        multimap.put("程序员","小明");
        multimap.put("程序员","傻逼");
        Collection<String> 程序员 = multimap.get("程序员");
        System.out.println(multimap);

        // 不可变集合
        ImmutableList<String> immutableList = new ImmutableList.Builder<String>().add("哈哈哈").add("嘻嘻嘻").build();

        // 集合拆分Lists.partition
        List<String> stringList = Lists.newArrayList();
        for (int i = 0; i <= 500; i++) {
            stringList.add(i+"");
        }

        Lists.partition(stringList,100).forEach(System.out::println);

        // 计算性能耗时
        Stopwatch started = Stopwatch.createStarted();
        // TODO
        // 业务代码
        System.out.println(UUID.randomUUID());
        System.out.println(started.elapsed(TimeUnit.MILLISECONDS));

        // map比较
        Map<String,String> map = Maps.newHashMap();
        Map<String,String> map1 = Maps.newHashMap();

        map.put("1","上海");
        map.put("2","北京");
        map.put("3","广州");
        map1.put("1","上海");
        map1.put("2","北京");
        map1.put("3","深圳");
        MapDifference<String, String> difference1 = Maps.difference(map, map1);
        // 只有左集合有
        difference1.entriesOnlyOnLeft().entrySet().forEach(entry -> System.out.println("left:"+entry.getKey() + ":" + entry.getValue()));
        // 只有右集合有
        difference1.entriesOnlyOnRight().entrySet().forEach(entry -> System.out.println("right:"+entry.getKey() + ":" + entry.getValue()));
        // 交集
        difference1.entriesInCommon().entrySet().forEach(entry -> System.out.println("common:"+entry.getKey() + ":" + entry.getValue()));
    }
}
