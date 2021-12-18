package com.rp.sec11SinkTelecast;

import com.rp.coureutil.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lec01SinkOne {
    public static void main(String[] args) {
        //mono of one value
        Sinks.One<Object> sink = Sinks.one();
        //sink.tryEmitValue("Hi");
       /* sink.emitValue("Hey",(signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false;
        });
        sink.emitValue("Hey",(signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false; //if it is true it will retry and if it again get failed than this loop will keep going
        });*/
        Mono<Object> mono = sink.asMono();
        mono.subscribe(Util.subscriber("Sumit"));
        mono.subscribe(Util.subscriber("Sam"));
        sink.tryEmitValue("Hello");
        //sink.tryEmitEmpty();

    }
}
