package com.zhougl.wxpaydemo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author by zhougl
 * @date 2019/9/3 23:54
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hello")
    public String test(){
        System.out.println("hello,world!");
        return "hello";
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("shabi");

        list.stream()

        System.out.println(list);

        Optional<List<String>> list1 = Optional.of(list);
        

        list.removeIf(s -> s.contains("sha"));

        System.out.println(list);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if(p.test(t)){
                result.add(t);
            }
        }
        return result;
    }

    public static <T,R> List<R> filter(List<T> list, Function<T,R> f){
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }

    public static <T> void filter(List<T> list, Consumer<T> consumer){
        for (T t : list) {
            consumer.accept(t);
        }
    }

}
