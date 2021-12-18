package com.rp.sec04Operators;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

public class OperatorHandle {
    public static void main(String[] args) {
        //handle =filter+map
        Flux.range(1,20)
                .handle((integer, synchronousSink) -> {
                    if(integer%2==0){
                        synchronousSink.next(integer); //filter
                    }else{
                        synchronousSink.next("Odd no :"+integer); //map
                    }
                }).subscribe(Util.subscriber());
        System.out.println("...........");
        Flux.create(fluxSink -> {
            while (!fluxSink.isCancelled()){
                fluxSink.next(Util.getFaker().animal().name());
            }
        }).handle((o, synchronousSink) -> {
            synchronousSink.next(o);
            if(o.toString().equalsIgnoreCase("zebra")){
                synchronousSink.complete();
            };
        }).subscribe(Util.subscriber());
    }
}
