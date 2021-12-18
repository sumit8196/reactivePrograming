package com.rp.sec04Operators;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class DelayOperator {
    public static void main(String[] args) {
        //we can emit data after some interval than we can use Flux.interval also
        //it will be scheduled in different thread pool
        //So by default initial request will be 32 or it will look in the system propery "reactor.bufferSize.x"
        // and if it find it will take the max of 8 and set in the system property.
        System.setProperty("reactor.bufferSize.x","40");
        Flux.range(1,100)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());

        Util.sleep(100);
    }
}
