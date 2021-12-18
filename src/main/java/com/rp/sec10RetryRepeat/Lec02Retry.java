package com.rp.sec10RetryRepeat;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class Lec02Retry {
    private static AtomicInteger atomicInteger=new AtomicInteger(1);
    public static void main(String[] args) {
        integerFlux()
                //.repeat(2)//repeat two times so total times producer will produce is three
                .retry(2)
                .subscribe(Util.subscriber());
    }
    private static Flux<Integer> integerFlux(){
        return Flux.range(1,30)
                .doOnSubscribe(s-> System.out.println("---Subscribed"))
                .doOnComplete(()-> System.out.println("---completed"))
                .map(i->atomicInteger.getAndIncrement())
                .map(i->i/(Util.getFaker().random().nextInt(1,10)>6?0:1))
                .doOnError(i-> System.out.println("error: "+i))
                ;
    }
}
