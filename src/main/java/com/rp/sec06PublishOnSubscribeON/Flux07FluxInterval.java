package com.rp.sec06PublishOnSubscribeON;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class Flux07FluxInterval {
    public static void main(String[] args) {

        Flux.interval(Duration.ofSeconds(1))
                .subscribe();
        //it will exit immediatly because it internally uses scheduler.parallel execution.
        //Also we cannot  change the schedular from parralel to any other schedular like boundelastic
    }
    private static void printThreadName(String msg){
        System.out.println(msg+"\tThread:\t."+Thread.currentThread().getName());
    }
}
