package com.rp.sec04Operators;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

public class LecFLuxImmutable {
    public static void main(String[] args) {
        Flux.range(1,10)
                .map(i->i*10)
                .subscribe(Util.subscriber());

        //flux is immutable so
        Flux<Integer> flux=Flux.range(1,10);
        flux.map(i->i*10);
        flux.filter(i->i>5);
        flux.subscribe(Util.subscriber());

    }
}
