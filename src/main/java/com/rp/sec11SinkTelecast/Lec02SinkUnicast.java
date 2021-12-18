package com.rp.sec11SinkTelecast;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec02SinkUnicast {
    public static void main(String[] args) {
        //hankde through which we will push items. It is exposed to publisher
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

        //handle through which subscriber will receive data. It is exposed to subscriber.
        Flux<Object> flux = sink.asFlux();
        flux.subscribe(Util.subscriber("sumit"));
        flux.subscribe(Util.subscriber("sam"));//it will receive the error
        sink.tryEmitNext("Hi Sumit");
        sink.tryEmitNext("How are you ");
        sink.tryEmitNext("??");

    }
}
