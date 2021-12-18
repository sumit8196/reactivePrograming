package com.rp.sec03Flux;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

public class FluxGenerate {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
            synchronousSink.next(Util.getFaker().ancient().hero());
            synchronousSink.complete();
            //synchronousSink is single instances, it can be called only one time.so each time it give new instance of synchronousSink
            //At most one next call be there
            //synchronousSink.next(Util.getFaker().ancient().hero());
        })
                .take(3)
                .subscribe(Util.subscriber());
    }
}
