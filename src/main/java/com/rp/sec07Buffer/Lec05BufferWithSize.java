package com.rp.sec07Buffer;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec05BufferWithSize {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");
        //75 % 16=12

        Flux.create(fluxSink -> {
            for (int i = 0; i <201 && !fluxSink.isCancelled(); i++) {
                fluxSink.next(i);
                System.out.println(" Pushed: "+i);
                Util.sleepMili(1);
            }
            fluxSink.complete();
        })
                .onBackpressureBuffer(20,(o)-> System.out.println("Dropped: "+o))
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(o -> {
                    Util.sleepMili(10);
                })
                .subscribe(Util.subscriber());
        Util.sleep(20);
    }
}
