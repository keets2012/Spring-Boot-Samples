package com.blueskykong.reactor.webflux;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.IntStream;


public class ReactorTest {

    private static final Logger logger = Logger
            .getLogger(ReactorTest.class.getName());

    @Override
    public String toString() {
        System.out.println("这个方法执行了, 耗时1秒钟");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "LogDemo";
    }

    public void test() {
        // 如果不加判断直接打印, 会有额外多余的开销, 就算最终日志并没有打印
        logger.fine("打印一些日志:" + this);
    }

    public static void main(String[] args) {
        ReactorTest demo = new ReactorTest();
        demo.test();

        int[] nums = {1, 2, 3};
        // 外部迭代
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        System.out.println("结果为：" + sum);

        // 使用stream的内部迭代
        // map就是中间操作（返回stream的操作）
        // sum就是终止操作
        int sum2 = IntStream.of(nums).map(ReactorTest::doubleNum).sum();
        System.out.println("结果为：" + sum2);

        System.out.println("惰性求值就是终止没有调用的情况下，中间操作不会执行");
        IntStream.of(nums).map(ReactorTest::doubleNum);

    }


    public static int doubleNum(int i) {
        System.out.println("执行了乘以2");
        return i * 2;
    }


}
