package com.rp.sec04Operators;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class TimeoutOperator {
    public static void main(String[] args) {
        //timeout for each emission not all the data it requested.It is time between two signal/
            getOrderNumber()
                    .timeout(Duration.ofSeconds(2),fallBack())
                    .subscribe(Util.subscriber());

            Util.sleep(50);
    }
    private static Flux<Integer> getOrderNumber(){
        return Flux.generate(integerFluxSink -> {
                Util.sleep(Util.getFaker().random().nextInt(1,4));
                integerFluxSink.next(Util.getFaker().random().nextInt(1,100));
        })
                ;
    }
    private static Flux<Integer> fallBack(){
        return Flux.range(100,200).delayElements(Duration.ofSeconds(1));
    }
}
