package com.rp.sec06PublishOnSubscribeON;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

public class Lec01ThreadDemo {
    public static void main(String[] args) {
        Flux<Object> create = Flux.create(sink -> {
            Util.sleep(2);
            printThreadName("create");
            sink.next(1);
        })
                .doOnNext(i -> printThreadName("next" + i));
        Runnable runnable=()->create.subscribe(v->printThreadName("subscription: "+v));
        for(int i=0;i<2;i++){
            System.out.println("for loop"+"\tThread:\t."+Thread.currentThread().getName());
            new Thread(runnable).start();
        }
        System.out.println("end"+"\tThread:\t."+Thread.currentThread().getName());
    }
    private static void printThreadName(String msg){
        System.out.println(msg+"\tThread:\t."+Thread.currentThread().getName());
    }
}
