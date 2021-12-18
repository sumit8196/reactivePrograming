package com.rp.sec07Buffer;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec01Demo {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            for (int i = 0; i <501 ; i++) {
                fluxSink.next(i);
                System.out.println(" Pushed: "+i);
            }
            fluxSink.complete();
        }).publishOn(Schedulers.boundedElastic())
                .doOnNext(o -> {
                    Util.sleepMili(10);
                })
                .subscribe(Util.subscriber());
        Util.sleep(20);
    }
}
