package com.rp.sec09Batching;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec04Window {
    private static AtomicInteger atomicInteger=new AtomicInteger(1);
    public static void main(String[] args) {
        eventStream()
                //.window(3)
                .window(Duration.ofSeconds(2))
                //.map(Lec04Window::saveEvents)  //it wil give the mono again .
                .flatMap(Lec04Window::saveEvents)
                //.flatMap(stringFlux -> Flux.just(stringFlux))
                .subscribe( Util.subscriber()); //subsrcibing parant publsiher.

        Util.sleep(30);
    }
    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(400))
                .take(1)
                .map(i->"event: "+i);
    }
    private static Mono<Integer> saveEvents(Flux<String>stringFlux){
        return stringFlux
                .doOnNext(e-> System.out.println("saving this:\t"+e))
                .doOnComplete(()-> {
                    System.out.println("Saved this batch");
                    System.out.println(".............");
                })
                .then(getAtomicInteger())  //it will invoke once it will get onComplete signal
                ;
    }
    private static Mono<Integer> getAtomicInteger(){
        System.out.println("Returning getAtomic integer");
       return Mono.just(atomicInteger.getAndIncrement());
    }
}
