package com.rp.sec06PublishOnSubscribeON;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec05PubSubOn {
    public static void main(String[] args) {
        Flux<Object> create = Flux.create(sink -> {
            printThreadName("create ");
            for (int i = 0; i <3 ; i++) {
                sink.next(i);
                Util.sleep(1);
            }
            sink.complete();
        }).doOnNext(i -> printThreadName("next1 " + i));

          create.publishOn(Schedulers.parallel())
                .doOnNext(i->printThreadName("next2 "+i))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(o -> printThreadName("subscribe "+o))
        ;

        Util.sleep(20);
    }
    private static void printThreadName(String msg){
        System.out.println(msg+"\tThread:\t."+Thread.currentThread().getName());
    }
}
