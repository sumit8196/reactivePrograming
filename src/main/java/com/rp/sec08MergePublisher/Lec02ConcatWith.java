package com.rp.sec08MergePublisher;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec02ConcatWith {
    public static void main(String[] args) {
        Flux<String> flux1 = Flux.just("a", "b");
        Flux<String> flux2=Flux.error(new RuntimeException("oops"));
        Flux<String> flux3 = Flux.just("c", "d", "e");


        Flux<String> flux=flux1.concatWith(flux2);
        Flux<String> stringFlux=Flux.concat(flux1,flux2,flux3);
        Flux<String> stringFlux2=Flux.concatDelayError(flux1,flux2,flux3);

       // flux2.startWith(flux1).subscribe(Util.subscriber());
        System.out.println("............");
        //flux.subscribe(Util.subscriber());
        stringFlux2.subscribe(Util.subscriber());
    }
}
