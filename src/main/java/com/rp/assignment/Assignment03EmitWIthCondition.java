package com.rp.assignment;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class Assignment03EmitWIthCondition {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Flux.generate((fluxSinkSynchronousSink) -> {
            // fluxSink.next(1);
            String country = Util.getFaker().country().name();
            fluxSinkSynchronousSink.next(country);

            if (country.equalsIgnoreCase("india") || atomicInteger.incrementAndGet() > 10) {
                fluxSinkSynchronousSink.complete();
            }
        })
                //.take(3)
                .subscribe(Util.subscriber());

        //other way to
        System.out.println("...............");

        Flux.generate(
                () -> {
                    System.out.println("starting the counter with " + 1);
                    return 1;
                },
                (state, fluxSinkSynchronousSink) -> {
                    state++;
                    fluxSinkSynchronousSink.next(1);
                    if(state>10){
                        fluxSinkSynchronousSink.complete();
                    }
                    return state;
                },
                (s) -> {
                    System.out.println("consumed: "+s);
                }

        ).subscribe(Util.subscriber());
    }
}
