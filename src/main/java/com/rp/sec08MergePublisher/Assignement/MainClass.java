package com.rp.sec08MergePublisher.Assignement;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class MainClass {
    public static void main(String[] args) {
       /* Flux.combineLatest(Car.getPrice(),CarFactor.getDemand(),((integer, aDouble) -> Double.valueOf(integer*aDouble)))
                .subscribe(Util.subscriber("latest car prise\t"));*/
        System.out.println("............");
        final int intialPrice=10000;
        Flux.combineLatest(monthStream(),demandStream(),(month,demand)-> (intialPrice-100*month)*demand)
                .subscribe(Util.subscriber());

        Util.sleep(30);

    }
    private static Flux<Long> monthStream(){
        return Flux.interval(Duration.ZERO,Duration.ofSeconds(1));
    }
    private static Flux<Double> demandStream(){
        return Flux.interval(Duration.ofSeconds(3))
                .map(i->Util.getFaker().random().nextInt(80,120)/100d)
                .startWith(1d);
    }
}
