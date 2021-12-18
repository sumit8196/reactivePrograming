package com.rp.sec10RetryRepeat;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;


public class Lec01Repeat {
    private static AtomicInteger atomicInteger=new AtomicInteger(1);
    public static void main(String[] args) {
         integerFlux()
                 //.repeat(2)//repeat two times so total times producer will produce is three
                 .repeat(()->atomicInteger.get()<20)//repeat two times so total times producer will produce is three
                 .subscribe(Util.subscriber());
    }
    private static Flux<Integer> integerFlux(){
        return Flux.range(1,3)
                .doOnSubscribe(s-> System.out.println("---Subscribed"))
                .doOnComplete(()-> System.out.println("---completed"))
                .map(i->atomicInteger.getAndIncrement())
                //.map(i->i/0)
                ;
    }
}
