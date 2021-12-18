package com.rp.sec2;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxFromPublisher {
    public static void main(String[] args) {
        //flux from mono
        //such condition come when one method receive flux but you have mono then you need the conversion.
        Mono<String> mono=Mono.just("a");
       // doSomething(Flux.from(mono));
        //Mono to flux
        Flux<Integer> integerFlux=Flux.range(1,10);
        //doSomething(Mono.from(integerFlux));
        //Another way to convert flux to mono is .next()
       // integerFlux.filter(i->i==7).next().subscribe(Util.onNext());
        //take operator
        Flux.range(1,10)
                .take(3) //after 3rd element he cancel the subscription and issues the complete signal downstream
                .subscribe(Util.subscriber());
    }
    private static void doSomething(Flux<String> flux){
        flux.subscribe(Util.onNext());
    }
    private static void doSomething(Mono<Integer> mono){
        mono.subscribe(Util.onNext());
    }
}
