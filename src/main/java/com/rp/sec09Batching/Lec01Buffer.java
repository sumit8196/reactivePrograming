package com.rp.sec09Batching;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01Buffer {
    public static void main(String[] args) {
            eventStream()
                    //.buffer(Duration.ofSeconds(2))
                    .bufferTimeout(5,Duration.ofSeconds(2))
                   // .buffer(3)
                    //.collectList()
                    .subscribe(Util.subscriber());

            Util.sleep(30);
    }
    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(400))
                //.take(30)
                .map(i->"event: "+i);
    }
}
