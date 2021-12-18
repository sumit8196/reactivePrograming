package com.rp.sec08MergePublisher.Assignement;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Car {
    static AtomicInteger integer=new AtomicInteger(50);
    public static Flux<Integer> getPrice(){
        return Flux.interval(Duration.ofSeconds(1))
                .map(aLong -> integer.getAndDecrement());
    }
}
