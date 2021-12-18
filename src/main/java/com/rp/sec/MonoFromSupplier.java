package com.rp.sec;

import com.rp.coureutil.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class MonoFromSupplier {
    public static void main(String[] args) throws InterruptedException {
        //use just method only when you have already data.Below line is wrong.It will excute getNmae mehtod.So just is not meant to be use like below
       //Mono<String> mono= Mono.just(getName());

        //when data is not available then use other ways to create mono like supplier
        Mono<String> mono=Mono.fromSupplier(()-> getName());
        mono.subscribe(
                Util.onNext()
        );
        Supplier<String> supplier=()->getName();
        //Even we can create mono from callable.
        Callable<String> callable=()->getName();
            Mono.fromCallable(callable).subscribe(Util.onNext());
        System.out.println(".......");
        getNameMono();
        getNameMono()
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(Util.onNext());
        getNameMono();
        Util.sleep(3);

    }
    public static String getName() {
        //Thread.sleep(2000);
        System.out.println("Generating name:  ");
        return Util.getFaker().name().nameWithMiddle();
    }
    public static Mono<String> getNameMono() {
        System.out.println("get name:  ");
        return Mono.fromSupplier(()->{
            System.out.println("Generating name:  ");
            Util.sleep(1);
            return Util.getFaker().name().nameWithMiddle();
        }).map(String::toUpperCase);
    }
}
