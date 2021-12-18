package com.rp.sec11SinkTelecast;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Lec05SinkMultiDirectAll {
    public static void main(String[] args) {
        //hankde through which we will push items. It is exposed to publisher
        Sinks.Many<Object> sink = Sinks.many().multicast().directBestEffort();

        System.setProperty("reactor.bufferSize.small", "16");

        //handle through which subscriber will receive data. It is exposed to subscriber.
        Flux<Object> flux = sink.asFlux();
        flux.subscribe(Util.subscriber("sumit"));//it is fast
        flux.delayElements(Duration.ofMillis(100))
                .subscribe(Util.subscriber("sam"));//it is slow
        for (int i = 0; i < 100; i++) {
            sink.tryEmitNext(i);
        }
        Util.sleep(5);

    }
}
