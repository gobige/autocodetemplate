package com.example.autocodetemplate.service;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import javax.servlet.*;
import java.io.IOException;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class HystrixTest {
   public static final  String switcher = "primarySecondary.usePrimary";

    @Test
    public void testSynchronous() {
        assertEquals("Hello World!", new CommandHelloWorld("World").execute()); // queue().get()  // 同步执行
        assertEquals("Hello Bob!", new CommandHelloWorld("Bob").execute());
    }

    @Test
    public void testWithCacheHits() {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            CommandHelloWorld command2a = new CommandHelloWorld("yates");
            CommandHelloWorld command2b = new CommandHelloWorld("yates");

            System.out.println(command2a.execute());
            assertFalse(command2a.isResponseFromCache());

            // 从缓存中加载
            System.out.println(command2b.execute());
            assertTrue(command2b.isResponseFromCache());
        } finally {
            context.shutdown();
        }

        // 新建一个请求 新的context
        context = HystrixRequestContext.initializeContext();
        try {
            CommandHelloWorld command3b = new CommandHelloWorld("yates");
            System.out.println(command3b.execute());
            // this is a new request context so this
            // should not come from cache
            assertFalse(command3b.isResponseFromCache());
        } finally {
            context.shutdown();
        }
    }

    @Test
    public void UnitTest () {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            ConfigurationManager.getConfigInstance().setProperty(switcher, true);
            CommandHelloWorld command2a = new CommandHelloWorld("yates");
            System.out.println(command2a.execute());

            CommandHelloWorld command2b = new CommandHelloWorld("yates2");
            ConfigurationManager.getConfigInstance().setProperty(switcher, false);
            System.out.println(command2b.execute());

        } finally {
            context.shutdown();
        }
    }

    @Test
    public void testAsynchronous2() throws Exception {

        Future<String> fWorld = new CommandHelloWorld("World").queue();
        Future<String> fBob = new CommandHelloWorld("B2ob").queue();

        assertEquals("Hello World!", fWorld.get());  //  异步执行
        assertEquals("Hello Bob!", fBob.get());
    }
    @Test
    public void testAsynchronous3() throws Exception {
        Observable<String> ho = new CommandHelloWorld("World").observe(); // toObservable().toBlocking().toFuture()   Hot Observable 不论 “事件源” 是否有“订阅者” * 都会在创建后对事件进行发布
        Observable<String> co = new CommandHelloWorld("World").toObservable(); // Cold Observable在没有 “订阅者” 的时候并不会发布事件， 而是进行等待，知道有 “订阅者” 之后才发布事件

        ho.subscribe(new Action1<String>() {

            @Override
            public void call(String s) {
                // value emitted here
            }

        });
    }
}

/**
 *
 */
class  CommandHelloWorld extends HystrixCommand<String> {

    // 新老流程逻辑开关·
    private final static DynamicBooleanProperty usePrimary = DynamicPropertyFactory.getInstance().getBooleanProperty(HystrixTest.switcher, true);

    private final String name;

    // 配置请求所在统计组，请求名称，请求所分配线程组
    public static final Setter config; // 使用信号量隔离

    private static final HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("helloWorld");

    static {
        config = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andCommandKey(commandKey)
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("thread pool 1"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE) // 隔离策略
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(10) // 信号量隔离，最大并发数 默认10
                        .withRequestCacheEnabled(true) // 是否开启缓存
                        .withExecutionTimeoutEnabled(true) // 是否开启超时 默认开启
                        .withExecutionIsolationThreadInterruptOnTimeout(true) // 超时后是否中断 默认开启中断
                        .withExecutionTimeoutInMilliseconds(1000) // 超时时间
                        .withFallbackEnabled(true) // 当错误或超时发生时，是否走降级策略，默认为 true
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(10) // 降级调用，最大并发数 默认10
                        .withCircuitBreakerEnabled(true) // ：是否开启熔断器，默认为 true
                        .withCircuitBreakerRequestVolumeThreshold(20) // 滑动窗口大小，即触发熔断的最小请求数量，默认为 20
                        .withCircuitBreakerSleepWindowInMilliseconds(5000) // 当触发熔断后，多少秒之后再次进行访问尝试 默认为 5000ms
                        .withCircuitBreakerErrorThresholdPercentage(50) // 失败的请求达到这个值时，就触发熔断，默认为 50%
                        .withCircuitBreakerForceClosed(false)
                        .withMetricsHealthSnapshotIntervalInMilliseconds(500) // 收集健康指标 时间间隔 默认500
                        .withMetricsRollingStatisticalWindowInMilliseconds(1000) // 指标收集滑窗时间
                        .withMetricsRollingPercentileBucketSize(10) // 滑窗划分bukect大小
                        .withMetricsRollingPercentileEnabled(true) // 百分比，均值指标收集
                );


    }


    public CommandHelloWorld(String name) {
        super(config);
        this.name = name;
    }

    @Override
    protected String run() {
        // 真正请求调用方法
        if (usePrimary.get()) {
            return new PrimaryCommand(name).execute();
        } else {
            return new SecondCommand(name).execute();
        }

//        throw new RuntimeException("throw exception！");
//        return "Hello " + name + "!";
    }

    // 降级策略 返回数据
    @Override
    protected String getFallback() {
//        return " 优雅降级！";

        return new FallbackViaNetwork(name).execute(); // 和主线程分开，新开一个线程，重新封装command，进行fallback远程调用获取默认值，
    }

    // 设置缓存key
    @Override
    protected String getCacheKey() {
        // 缓存内容
        return String.valueOf(name);
    }

    /**
     * 清理缓存  在同一个request context 中  同时 写请求和 读请求下 会导致 读取到失效数据，设置缓存失效实现 调用
     * @param name 这里当做cacheKey
     */
    public static void flushCache(String name) {
        HystrixRequestCache.getInstance(commandKey, HystrixConcurrencyStrategyDefault.getInstance()).clear(name);
    }

}

class PrimaryCommand extends HystrixCommand<String> {
    private final String name;

    public static final Setter config = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup2"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("PrimaryCommand"))
            .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("thread pool PrimaryCommand"));

    public PrimaryCommand(String name) {
        super(config);
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "PrimaryCommand response";
    }
}

class SecondCommand extends HystrixCommand<String> {
    private final String name;

    public static final Setter config = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup3"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("SecondCommand"))
            .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("thread pool SecondCommand"));

    public SecondCommand(String name) {
        super(config);
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "SecondCommand response";
    }
}

/**
 * 远程默认数据调用返回
 */
class FallbackViaNetwork extends HystrixCommand<String> {
    private final String name;

    protected FallbackViaNetwork(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceX"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueFallbackCommand"))
                // 需要一个新的线程池
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("RemoteServiceXFallback")));
        this.name = name;
    }

    @Override
    protected String getFallback() {
        return "远程缓存也拿不到";
    }

    @Override
    protected String run() throws Exception {
        return "从redis 远程 获取 缓存默认数据";

    }
}

/**
 * 通过filter链 进行  HystrixRequestContext 生命周期管理
 */
  class HystrixRequestContextServletFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            chain.doFilter(request, response);
        } finally {
            context.shutdown();
        }
    }
}


// HystrixCollapser 批量请求在一个 HystrixCommand处理

class CommandHelloWorld2 extends HystrixObservableCommand<String> {

    private final String name;

    public CommandHelloWorld2(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        // a real example would do work like a network call here
                        subscriber.onNext("Hello");
                        subscriber.onNext(name + "!");
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}