package com.rp.service;

import com.rp.coureutil.Util;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Assignement01 {
    public static void main(String[] args) {
       /* Flux.range(1,20)
                .publishOn(Schedulers.boundedElastic())
                .*/
        /*Flux.range(1,10)
                //.doOnNext(System.out::println)
                //.doOnComplete(()-> System.out.println("completed.."))
                .then(getMono())
        .subscribe(Util.subscriber());*/
        Flux.range(1,10)
                .filterWhen(Assignement01::getBooleanFLux)
                .map(integer ->{
                    System.out.println("Inside filter when");
                    return integer;
                })
                .subscribe();
        /*Flux.range(1,10)
                .flatMap(Assignement01::getBooleanMono)
                .filter(booleanMono -> booleanMono)
                .map(integer ->{
                    System.out.println("Inside filter");
                    return integer;
                })
                .subscribe();
*/



        Util.sleep(3);
    }


    private static Mono<Integer> getMono(){
        return Mono.just(19);
    }
    private static Mono<Boolean> getBooleanMono(Integer integer){
        System.out.println(integer);
        return Mono.just(Util.getFaker().random().nextBoolean());
    }
    private static Flux<Boolean> getBooleanFLux(Integer integer){
       return Flux.range(1,10)
                .map(integer1 -> {
                    System.out.println("inside getBooleanFLux"+integer1);
                    if(integer1%2==1){
                        return true;
                    }
                    return false;
                })
               .doOnCancel(()-> System.out.println("completed..."))
               .doOnComplete(()-> System.out.println("completed..."))
               ;
    }


}
