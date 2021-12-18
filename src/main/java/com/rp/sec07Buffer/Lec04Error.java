package com.rp.sec07Buffer;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec04Error {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");
        //75 % 16=12
// when queue is full null Error: The receiver is overrun by more signals than expected (bounded queue...)
        Flux.create(fluxSink -> {
            for (int i = 0; i <201 && !fluxSink.isCancelled(); i++) {
                fluxSink.next(i);
                System.out.println(" Pushed: "+i);
                Util.sleepMili(1);
            }
            fluxSink.complete();
        })
                .onBackpressureError()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(o -> {
                    Util.sleepMili(10);
                })
                .subscribe(Util.subscriber());
        Util.sleep(20);
    }
}
