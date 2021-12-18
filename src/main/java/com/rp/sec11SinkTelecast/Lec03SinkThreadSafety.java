package com.rp.sec11SinkTelecast;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Lec03SinkThreadSafety {
    public static void main(String[] args) {
        //hankde through which we will push items. It is exposed to publisher
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

        //handle through which subscriber will receive data. It is exposed to subscriber.
        Flux<Object> flux = sink.asFlux();
        List<Object> list=new ArrayList<>();
        flux.subscribe(list::add);
        /*for (int i = 0; i < 1000; i++) {
            final int j=i;
            //Multiple thread will run and try to emit data in the sink.If it is thread safe than it size will be 1000
            //It is thread safe.
            CompletableFuture.runAsync(()->{
                sink.tryEmitNext(j);
            });
        }*/
        for (int i = 0; i < 1000; i++) {
            final int j=i;
            //Multiple thread will run and try to emit data in the sink.
            CompletableFuture.runAsync(()->{
                sink.emitNext(j,(s,e)->true);
            });
        }

        Util.sleep(3);
        System.out.println(list.size());  //by default sink is thread safe.
    }
}
