package com.rp.sec07Buffer;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class Lec02Drop {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");
        //75 % 16=12
        //output will be first 16 element will be printed than 119(whenever 75% of buffer size is empty it will store the latest pushed element)
        // ,120 like that it will be printed as it is the latest element in
        //the queue
        List<Object> list=new ArrayList<>();
        Flux.create(fluxSink -> {
            for (int i = 0; i <201 ; i++) {
                fluxSink.next(i);
                System.out.println(" Pushed: "+i);
                Util.sleepMili(1);
            }
            fluxSink.complete();
        })
                .onBackpressureDrop(list::add) //will add the dropped value in the list
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(o -> {
                    Util.sleepMili(10);
                })
                .subscribe(Util.subscriber());
        Util.sleep(20);
        System.out.println(list);
    }
}
