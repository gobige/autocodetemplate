package com.example.autocodetemplate.ohter.practice.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * 响应式编程是面向数据流和变化传播的编程范式。
 * 方便的表达静态和动态数据流，相关计算模型会自动将变化的值通过数据流进行传播
 *
 * Oerators  推送模式
 * 就是一个Publisher/Subscriber
 * 在调用 subscribe前任何都不会发生
 * Flux：0或N个元素  OnNext()  onComplete() onError()
 * Mono：0或1个元素  OnNext()  onComplete() onError()
 *
 * BackPressure  反压力
 * Subscription，上游速度生成速度快，控制下游消费的速度 onRequest() onCancel() onDispose()
 *
 * 线程调度Schedulers
 * 元素pushlish到哪个线程上去
 * immediate()  single()  newSingle() 线程
 * elastic() parallel() newParallel 线程池
 *
 * 错误处理
 * onError() onErrorReturn()/onErrorResume()
 * doOnError()/doFinally()
 */
@Slf4j
public class ReactorTest {
    public static void main(String[] args) throws Exception{
        Flux.range(1, 6)
                .doOnRequest(n -> log.info("Request {} number", n)) // 注意顺序造成的区别
//				.publishOn(Schedulers.elastic())
                .doOnComplete(() -> log.info("Publisher COMPLETE 1"))
                .map(i -> {
                    log.info("Publish {}, {}", Thread.currentThread(), i);
                    return 10 / (i - 3);
//					return i;
                })
                .doOnComplete(() -> log.info("Publisher COMPLETE 2"))
//				.subscribeOn(Schedulers.single())
//				.onErrorResume(e -> {
//					log.error("Exception {}", e.toString());
//					return Mono.just(-1);
//				})
//				.onErrorReturn(-1)
                .subscribe(i -> log.info("Subscribe {}: {}", Thread.currentThread(), i),
                        e -> log.error("error {}", e.toString()),
                        () -> log.info("Subscriber COMPLETE")//,
//						s -> s.request(4)
                );
        Thread.sleep(2000);

    }
}
