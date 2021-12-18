package com.rp.sec11SinkTelecast;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec05SinkReply {
    public static void main(String[] args) {
        //hankde through which we will push items. It is exposed to publisher
        Sinks.Many<Object> sink = Sinks.many().replay().all();
        //Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();

        //handle through which subscriber will receive data. It is exposed to subscriber.
        Flux<Object> flux = sink.asFlux();
        //flux.subscribe(Util.subscriber("sumit"));

        sink.tryEmitNext("Hi Sumit");
        sink.tryEmitNext("How are you ");
        flux.subscribe(Util.subscriber("sumit: "));
        flux.subscribe(Util.subscriber("sam: "));
        sink.tryEmitNext("??");
        flux.subscribe(Util.subscriber("Jake: "));
        sink.tryEmitNext("New message ");

    }
}
