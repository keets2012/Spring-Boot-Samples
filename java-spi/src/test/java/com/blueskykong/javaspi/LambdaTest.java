package com.blueskykong.javaspi;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author keets
 * @data 2018/8/1.
 */
public class LambdaTest {


    public static void main(String[] args) {
        List<String> list11 = Arrays.asList("123", "234");
        int sum = list11.stream()
                .map(e -> Integer.valueOf(e))
                .reduce(100, (s1, s2) -> s1 + s2);
        System.out.println(sum);
        Map<String, List<String>> list12 = list11.stream().collect(Collectors.toMap(s -> s, Collections::singletonList));
        List<String> list2 = Arrays.asList("hello", "hi", "你好");
        List<String> list3 = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");

        list2.stream().map(item -> list3.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList()).forEach(s -> s.forEach(str -> System.out.println("map: " + str)));

        list2.stream().flatMap(item -> list3.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList()).forEach(s -> System.out.println("flatmap: " + s));

        /**获取单词，并且去重**/
        List<String> list = Arrays.asList("hello welcome", "world hello", "hello world",
                "hello world welcome");

        List<String[]> strings = list.stream().map(str -> str.split(" ")).distinct().collect(Collectors.toList());


        //map和flatmap的区别
        list.stream().map(item -> Arrays.stream(item.split(" "))).distinct().collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("---------- ");
        list.stream().flatMap(item -> Arrays.stream(item.split(" "))).distinct().collect(Collectors.toList()).forEach(System.out::println);

        //实际上返回的类似是不同的
        List<Stream<String>> listResult = list.stream().map(item -> Arrays.stream(item.split(" "))).distinct().collect(Collectors.toList());
        List<String> listResult2 = list.stream().flatMap(item -> Arrays.stream(item.split(" "))).distinct().collect(Collectors.toList());

        System.out.println("----------");

        //也可以这样
        list.stream().map(item -> item.split(" ")).flatMap(Arrays::stream).distinct().collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("================================================");

        UnaryOperator<String> unaryOperator = str -> str + "-test";
        System.out.println(UnaryOperator.identity().apply("234"));
    }


    @Test
    public void testUnaryOperator() {
        UnaryOperator<String> unaryOperator = str -> str + "-test";
        System.out.println(UnaryOperator.identity());
        System.out.println(unaryOperator.apply("123"));
    }

    @Test
    public void testMapFun() {
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));

        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(map.get(3));

        map.computeIfPresent(9, (num, val) -> null);
        System.out.println(map.containsKey(9));

        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println(map.get(23));

        map.putIfAbsent(3, "bam");
        System.out.println(map.get(3));

        map.remove(3, "val3");
        System.out.println(map.get(3));

        //Merge时，如果键名不存在则插入，否则则对原键对应的值做合并操作并重新插入到map中
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));

        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));

    }

    @Test
    public void testParallelStream() {
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long t0 = System.nanoTime();

        long count = values.parallelStream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));

    }


    @Test
    public void testReduce() {
        List<String> list = Arrays.asList("ab", "abc", "abcd");
        Optional<String> reduce = list.stream().reduce((s1, s2) -> s1 + ":" + s2);
        reduce.ifPresent(s -> System.out.println(s));
    }

    @Test
    public void testMatch() {
        //允许检测指定的Predicate是否匹配整个Stream。所有的匹配操作都是最终操作，并返回一个boolean类型的值
        List<String> list = Arrays.asList("ab", "abc");

        boolean anyMatch = list.stream().map(String::toCharArray).anyMatch(array -> array.length == 3);

        boolean allMatch = list.stream().map(String::toCharArray).allMatch(array -> array.length == 3);

        boolean noneMatch = list.stream().map(String::toCharArray).noneMatch(array -> array.length == 3);

        System.out.println("anyMatch:" + anyMatch);
        System.out.println("allMatch:" + allMatch);
        System.out.println("noneMatch:" + noneMatch);
    }

    @Test
    public void testMap() {
        //中间操作map会将元素根据指定的Function接口来依次将元素转成另外的对象
        List<String> list = Arrays.asList("abe", "abc");
        //map返回的Stream类型是根据你map传递进去的函数的返回值决定
        list.stream().map(String::toCharArray).forEach(array -> System.out.println(array.length));
    }

    @Test
    public void testSort() {
        List<String> list = Arrays.asList("abe", "abc");

        list = list.stream().filter(s -> s.startsWith("a")).sorted().collect(Collectors.toList());
        list.stream().forEach(System.out::println);
    }

    @Test
    public void testOptional() {
        //用来防止NullPointerException异常的辅助类型
        List<String> list = Arrays.asList("ab", "bc");
        System.out.println(list.stream().findFirst().orElse("null str"));
        Optional<String> optional = Optional.of("hello");

        optional.isPresent();           // true
        optional.get();                 // "hello"
        optional.orElse("hi");    // "hello"

        optional.ifPresent((s) -> System.out.println("字符串不为空：" + s));
    }


    //Comparator
    @Test
    public void testComparator() {
        Comparator<String> comparator = String::compareTo;

        String str1 = "eeeabc";

        String str2 = "bcd";

        System.out.println("str比较大小：" + comparator.compare(str1, str2));

        System.out.println("str比较大小反转：" + comparator.reversed().compare(str1, str2));

    }


    @Test
    public void testConsumer() {
        //Consumer 接口表示执行在单个参数上的操作
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        Consumer<String> greeter = (p) -> System.out.println("Hello, " + p);
        greeter.andThen((t) -> System.out.println("now is :" + df.format(new Date()))).accept("Skywalker");

    }

    @Test
    public void testPredicate() {
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
        isEmpty.and(str -> str.equals("test"));
        System.out.println("tes: " + isEmpty.and(str -> str.equals("test")).test("tes"));

    }

    @Test
    public void testSupplier() {
        //Supplier 接口返回一个任意范型的值，和Function接口不同的是该接口没有任何参数
        Supplier sp = () -> "sp";
        System.out.println(sp.get());

    }

    @Test
    public void testFun() {
        //Function 接口有一个参数并且返回一个结果
        Function<String, Integer> toInteger = (t) -> Integer.valueOf(t);
        System.out.println("compose: " + toInteger.andThen(a -> a + 10).compose(str -> str + "1").apply("123"));

        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        Function<String, Integer> f = toInteger.compose(backToString);
        int str = f.apply("123");
        System.out.println(str);
    }


    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {

            if (predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

}
