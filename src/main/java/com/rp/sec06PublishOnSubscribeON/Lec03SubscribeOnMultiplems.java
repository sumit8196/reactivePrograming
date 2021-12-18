package com.rp.sec06PublishOnSubscribeON;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03SubscribeOnMultiplems {
    public static void main(String[] args) {
        Flux<Object> create = Flux.create(sink -> {
            printThreadName("create");
            for (int i = 0; i <2 ; i++) {
                sink.next(i);
                Util.sleep(1);
            }
            sink.complete();
        }).doOnNext(i -> printThreadName("next" + i));

       Runnable runnable=()->create.doFirst(()->printThreadName("first2"))
               .subscribeOn(Schedulers.boundedElastic())
               .doFirst(()->printThreadName("first1"))
               .subscribe(v->printThreadName("subscription: "+v));

        for (int i = 0; i <5 ; i++) {
            new Thread(runnable).start();
        }
        Util.sleep(20);
    }
    private static void printThreadName(String msg){
        System.out.println(msg+"\tThread:\t."+Thread.currentThread().getName());
    }
}
