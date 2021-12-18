package com.rp.sec04Operators;

import reactor.core.publisher.Flux;

public class DoCallBack {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            System.out.println("inside create");
            for(int i=0;i<5;i++){
                fluxSink.next(i);
            }
            fluxSink.complete();
            System.out.println("completed");
        })
                .doFirst(()-> System.out.println("doFirst: "))  //it will excuted after first1
                .doOnNext((i)-> System.out.println("doOnNext: "+i))
                .doOnComplete(()-> System.out.println("doOnComplete: "))
                .doOnSubscribe((s)-> System.out.println("doOnSubscribe: "+s))
                .doOnRequest(n-> System.out.println("doOnRequest : "+n))
                .doOnError(throwable -> System.out.println("doOnError: "+throwable.getMessage()))
                .doOnTerminate(()-> System.out.println("doOnTerminate: "))
                .doOnCancel(()-> System.out.println("doOnCancel: "))
                .doFirst(()-> System.out.println("doFirst: "))   //it will first excuted.
                .doFinally(signalType -> System.out.println("doFinally: signal type is: "+signalType))
                .doOnDiscard(Object.class,o-> System.out.println("doOnDiscard: "+o))
                .subscribe();
        //order will be
//        doFirst:
//        doOnSubscribe:
//        doOnRequest :
//        inside create
//        doOnNext: 0
//        doOnComplete:
//        doOnTerminate:
//        doFinally:


    }
}
