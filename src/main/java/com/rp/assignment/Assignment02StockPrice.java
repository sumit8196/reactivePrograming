package com.rp.assignment;

import com.rp.coureutil.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Assignment02StockPrice {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(1);
        getStockPrice().subscribe(new Subscriber<Integer>() {
            private Subscription subscription;
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
                subscription=s;
            }

            @Override
            public void onNext(Integer integer) {
                if(integer<-5 || integer>15){
                    System.out.println("Received invalid val so cancelling the subs: "+integer);
                    subscription.cancel();
                    countDownLatch.countDown();
                }else{
                    System.out.println("received:: "+integer);
                }
            }

            @Override
            public void onError(Throwable t) {
                countDownLatch.countDown();
            }

            @Override
            public void onComplete() {
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();

    }
    public static Flux<Integer> getStockPrice(){
        AtomicInteger atomicInteger = new AtomicInteger(10);
        return Flux.interval(Duration.ofSeconds(1))
                .map(i-> atomicInteger.accumulateAndGet(Util.getFaker().random().nextInt(-5,5),Integer::sum));
    }
}
