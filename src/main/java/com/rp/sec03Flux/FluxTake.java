package com.rp.sec03Flux;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

public class FluxTake {
    public static void main(String[] args) {
        Flux.create((fluxSink)-> {
            // fluxSink.next(1);
            String country= Util.getFaker().country().name();
            while(!country.equalsIgnoreCase("india") && !fluxSink.isCancelled()){
                System.out.println("Emiting: "+country);
                fluxSink.next(country);
                country= Util.getFaker().country().name();
            }
            fluxSink.complete();
        })
                .take(1)
                .log()
                .subscribe(Util.subscriber());
    }
}
