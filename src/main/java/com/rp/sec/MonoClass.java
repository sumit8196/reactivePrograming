package com.rp.sec;

import com.rp.coureutil.Util;
import reactor.core.publisher.Mono;

public class MonoClass {
    public  static void main(String[] arg){
        //publisher
        Mono<Integer> mono=Mono.just(1);
        //System.out.println(mono);//unless we subscribe the publisher it won't print anything
        mono.subscribe(num->{
             System.out.println("Recieved "+num);
        });
        //here mono.subscribe means subscriber are requesting data
        //and consumer(block of code written) is the onNext, so publisher(here mono) will call consumer(onNext) and data will start emitting
        //since we don't have onComplete and onError method so when publisher require to call these it will simple do nothing as subscriber
        //don't have these method.

        /*Mono subscribe*/
        Mono<String> mono2=Mono.just("ball");
        mono2.subscribe();//publisher will emit the data but since we didn't provide any onNext method so subscriber will not recieve any data
        mono2.subscribe(
                item ->System.out.println(item) ,//on next,
                 throwable -> System.out.println(throwable.getMessage()),  //onError
                () -> System.out.println("Data processing is completed")  //onComplete
            );

        Mono<Integer> mono3=Mono.just("ball")
                                .map(String::length)
                                .map(len->len/1);
        //if we don't provide the onError it will give nasty exception and exception stacktrace.
        /*mono3.subscribe(
                item ->System.out.println(item) ,//on next,
                throwable -> System.out.println(throwable.getMessage()),  //onError if
                () -> System.out.println("Data processing is completed")  //onComplete
        );*/
        /*mono3.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );*/
        System.out.println("....................");
        userRepository(3)
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete()
                );

    }
    private static Mono<String> userRepository(int userId){
        if(userId==1) {
            return Mono.just(Util.getFaker().name().fullName());
        }else if(userId==2){
            return Mono.empty();//don;t return null if data is not available.
        }else{
            return Mono.error(new RuntimeException("Not in range"));
        }
    }
}
