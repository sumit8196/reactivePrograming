package com.rp.sec06PublishOnSubscribeON;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02SubscribeOnDemo {
    public static void main(String[] args) {
        Flux<Object> create = Flux.create(sink -> {
            Util.sleep(2);
            printThreadName("create");
            sink.next(1);
        })
                .doOnNext(i -> printThreadName("next" + i))

                ;
       /*create
               .doFirst(()->printThreadName("first2"))
               .subscribeOn(Schedulers.boundedElastic())
               .doFirst(()->printThreadName("first1"))
               .subscribe(v->printThreadName("subscription: "+v));*/
       Runnable runnable=()->create
               .doFirst(()->printThreadName("first2"))
               .subscribeOn(Schedulers.boundedElastic())
               .doFirst(()->printThreadName("first1"))
               .subscribe(v->printThreadName("subscription: "+v));
        for (int i = 0; i <2 ; i++) {
            new Thread(runnable).start();
        }
       Util.sleep(2);
    }
    private static void printThreadName(String msg){
        System.out.println(msg+"\tThread:\t."+Thread.currentThread().getName());
    }
}
