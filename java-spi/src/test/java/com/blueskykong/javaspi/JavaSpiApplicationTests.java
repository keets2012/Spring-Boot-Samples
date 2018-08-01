package com.blueskykong.javaspi;

import com.blueskykong.javaspi.exception.ObjectSerializerException;
import com.blueskykong.javaspi.serializer.ObjectSerializer;
import com.blueskykong.javaspi.service.SerializerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaSpiApplicationTests {

    @Autowired
    private SerializerService serializerService;

    @Test
    public void serializerTest() throws ObjectSerializerException {
        ObjectSerializer objectSerializer = serializerService.getObjectSerializer();
        System.out.println(objectSerializer.getSchemeName());
        byte[] arrays = objectSerializer.serialize(Arrays.asList("1", "2", "3"));
        ArrayList list = objectSerializer.deSerialize(arrays, ArrayList.class);
        Assert.assertArrayEquals(Arrays.asList("1", "2", "3").toArray(), list.toArray());
    }

    @Test
    public void testScheduledExecutorService() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            List<String> list = Arrays.asList("1", "2", "3");

            CompletableFuture[] cfs = list.stream().map(str -> CompletableFuture.runAsync(() -> {
                System.out.println("========" + str);

                if (str.equals("2")) {
                    int i = 1 / 0;
                    System.out.println("---------");
                    return;
                }
                System.out.println("++++++++");
            }).exceptionally(e -> {
                System.out.println(e.getMessage());
                return null;
            }).whenComplete((v, e) -> System.out.println(v))).toArray(CompletableFuture[]::new);
            CompletableFuture.allOf(cfs).cancel(true);
        }, 1, 3, TimeUnit.SECONDS);
    }


    @Test
    public void thenCombine() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> s1 + " " + s2).join();
        System.out.println(result);
    }


    @Test
    public void testCom() {
        CompletableFuture<String> txManagerServerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            int i = 1 / 0;
            return "12";
        }).exceptionally(e -> {
            e.printStackTrace();
            return "ee";
        }).whenComplete((v, e) -> {
            System.out.println("vvvvvvvvvvvv + " + v);
            System.out.println("eeeeeeeeeeee + " + e);
        });
        String str = txManagerServerCompletableFuture.join();
        System.out.println("result: " + str);
    }


    @Test
    public void testFuture() throws InterruptedException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Integer> f = es.submit(() -> {
            // 长时间的异步计算
            Thread.sleep(2000L);
            System.out.println("长时间的异步计算");
            return 100;
        });
        while (true) {
            System.out.println("阻断");
            if (f.isDone()) {

                try {
                    System.out.println(f.get());
                    es.shutdown();
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(100L);
        }
    }


    public static CompletableFuture<Integer> compute() {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        return future;
    }

    @Test
    public void testSync() throws Exception {
        final CompletableFuture<Integer> f = compute();
        class Client extends Thread {
            CompletableFuture<Integer> f;

            Client(String threadName, CompletableFuture<Integer> f) {
                super(threadName);
                this.f = f;
            }

            @Override
            public void run() {
                try {
                    System.out.println(this.getName() + ": " + f.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        new Client("Client1", f).start();
        new Client("Client2", f).start();
        System.out.println("waiting");
        f.complete(100);
        //f.completeExceptionally(new Exception());
        System.in.read();
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        String f = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前时间为：" + df.format(new Date()));
            return "normal";
//            throw new ArithmeticException("illegal exception!");
        }).handleAsync((v, e) -> "value is: " + v + " && exception is: " + e).join();
        System.out.println(f);
    }

    @Test
    public void testHandle() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        String f = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前时间为：" + df.format(new Date()));
            return "normal";
//            throw new ArithmeticException("illegal exception!");
        }).handleAsync((v, e) -> "value is: " + v + " && exception is: " + e).join();
        System.out.println(f);
    }

    @Test
    public void testAnyAll() {
        Random rand = new Random();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100 + rand.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "first";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100 + rand.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "second";
        });
        CompletableFuture.allOf(future1, future2).join();
        Object cf = CompletableFuture.anyOf(future1, future2).join();
        System.out.println(cf);
    }


    @Test
    public void testApplyEither() {
        Random rand = new Random();
        String f = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100 + rand.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "first";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100 + rand.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "second";
        }), i -> "result: " + i).join();
        System.out.println(f);
    }

    @Test
    public void testCombine() {
        String f = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "first";
        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> "second"), (x, y) -> y + ":" + x).join();
        System.out.println(f);
    }


    @Test
    public void testCompose() {
        int f = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "first";
        }).thenCompose(str -> CompletableFuture.supplyAsync(() -> {
            String str2 = "second";
            return str.length() + str2.length();
        })).join();
        System.out.println("字符串长度为：" + f);

    }


    @Test
    public void testRun() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("执行CompletableFuture");
            return "first";
        }).thenRun(() -> System.out.println("finished")).join();
    }


    @Test
    public void testAcceptBoth() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "first";
        }).thenAcceptBoth(CompletableFuture.completedFuture("second"), (first, second) -> System.out.println(first + " : " + second)).join();
    }

    @Test
    public void testAccept() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world";
        }).thenAccept(System.out::println);
    }

    @Test
    public void testFConvert() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        String f = future.thenApplyAsync(i -> i * 10).thenApply(i -> i.toString()).join();
        System.out.println(f);
    }

    @Test
    public void testForCreate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        String result = CompletableFuture.supplyAsync(() -> {
            return df.format(new Date());
        }).thenApply(s -> "当前时间为： " + s).join();
        System.out.println(result);

        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sleep for 1s :" + df.format(new Date()));// new Date()为获取当前系统时间
        }).join();
    }


    @Test
    public void testComplete() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        CompletableFuture.runAsync(() -> {
            System.out.println("当前时间为：" + df.format(new Date()));

            throw new ArithmeticException("illegal exception!");
        }).exceptionally(e -> {
            System.out.println("异常为： " + e.getMessage());
            return null;
        }).whenComplete((v, e) -> System.out.println("complete")).join();
    }
}
