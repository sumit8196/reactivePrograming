package com.rp.sec04Operators;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

public class SwitchIfEmptyOperator {
    public static void main(String[] args) {
        getOrderNumber()
                .filter(i->i>10)
                .switchIfEmpty(fallBack())
                .subscribe(Util.subscriber());
    }
    private static Flux<Integer> getOrderNumber(){
        return Flux.range(1,10);
    }
    private static Flux<Integer> fallBack(){
        return Flux.range(100,200);
    }
}
