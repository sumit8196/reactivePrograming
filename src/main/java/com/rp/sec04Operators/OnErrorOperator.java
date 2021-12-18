package com.rp.sec04Operators;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OnErrorOperator {
    public static void main(String[] args) {
        //onErrorReturn
        Flux.range(1,100)
                .log()
                .map(integer -> 10/(5-integer))
                .onErrorReturn(-1)
                .subscribe(Util.subscriber());

        //onErrorResume
        System.out.println("..........onErrorResume.........");
        Flux.range(1,100)
                .log()
                .map(integer -> 10/(5-integer))
                //.log()
                .onErrorResume((throwabl)->fallBack())
                //.log()
                .subscribe(Util.onNext());
        //onErrorContinue
        System.out.println(".........OnErrorContinue.........");
        Flux.range(1,100)
                .log()
                .map(integer ->10/(integer%10-5))
                .onErrorContinue((throwabl,i)->fallBack())
                .subscribe(Util.onNext());
        //Mono.just(2).map(i->i/0).onErrorResume(throwable -> fallBack()).subscribe(Util.subscriber());
    }
    private static Mono<Integer> fallBack(){
        return Mono.just(-1);
    }
    private static Flux<Integer> fallBack2(){
        return Flux.range(1,10);
    }
}
