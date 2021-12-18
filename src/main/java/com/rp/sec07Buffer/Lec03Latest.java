package com.rp.sec07Buffer;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03Latest {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");
        //75 % 16=12
        //output will be first 16 element will be printed than 88(whenever 75% of buffer size is empty it will store the one latest onet)
        // ,89 like that it will be printed as it is the latest element in
        //the queue
        Flux.create(fluxSink -> {
            for (int i = 0; i <201 ; i++) {
                fluxSink.next(i);
                System.out.println(" Pushed: "+i);
                Util.sleepMili(1);
            }
            fluxSink.complete();
        })
                .onBackpressureLatest()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(o -> {
                    Util.sleepMili(10);
                })
                .subscribe(Util.subscriber());
        Util.sleep(20);
    }
}
