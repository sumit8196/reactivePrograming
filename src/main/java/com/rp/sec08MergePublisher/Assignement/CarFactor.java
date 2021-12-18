package com.rp.sec08MergePublisher.Assignement;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class CarFactor {
    public static Flux<Double> getDemand(){
        return Flux.interval(Duration.ofSeconds(1))
                .map(i-> Util.getFaker().random().nextInt(8,12))
                .map(i->Double.valueOf(i/10));
    }
}
