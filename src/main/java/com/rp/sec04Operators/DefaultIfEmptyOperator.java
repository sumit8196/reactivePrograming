package com.rp.sec04Operators;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

public class DefaultIfEmptyOperator {
    public static void main(String[] args) {
        getOrderNumber()
                .filter(i->i>10)
                .defaultIfEmpty(-100)
                .subscribe(Util.subscriber());
    }
    private static Flux<Integer> getOrderNumber2(){
        return Flux.generate(integerFluxSink -> {
            //Util.sleep(Util.getFaker().random().nextInt(1,4));
            integerFluxSink.next(Util.getFaker().random().nextInt(1,100));
        })
                ;
    }
    private static Flux<Integer> getOrderNumber(){
        return Flux.range(1,10);
    }
}
