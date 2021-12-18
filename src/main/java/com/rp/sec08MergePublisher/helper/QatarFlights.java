package com.rp.sec08MergePublisher.helper;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class QatarFlights {
    public static Flux<String> getFlights=Flux.range(1,5)
            .delayElements(Duration.ofSeconds(1))
            .map(i-> "Qatar: "+Util.getFaker().random().nextInt(100,999))
            .filter(i->Util.getFaker().random().nextBoolean());
}
