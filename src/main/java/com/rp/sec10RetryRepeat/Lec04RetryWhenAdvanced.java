package com.rp.sec10RetryRepeat;

import com.rp.coureutil.Util;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Lec04RetryWhenAdvanced {
    public static void main(String[] args) {
        orderService(Util.getFaker().business().creditCardNumber())
                .doOnError(e-> System.out.println(e.getMessage()))
                .retryWhen(Retry.from(
                        flux->flux
                        .doOnNext(rs->{
                            System.out.println(rs.totalRetries());
                            System.out.println(rs.failure());
                        })
                        .handle((retrySignal, synchronousSink) -> {
                            if(retrySignal.failure().getMessage().equals("500")){
                                synchronousSink.next(1);
                            }else{
                                synchronousSink.complete();
                            }
                        })
                        .delayElements(Duration.ofMillis(1000))
                ))
                .subscribe(Util.subscriber());
        Util.sleep(20);
    }



    //order service
private static Mono<String> orderService(String ccNumber){
    return Mono.fromSupplier(()->{
        processPayment();
        return Util.getFaker().idNumber().valid();
    });
}
    //payment service
    private static void processPayment(){
        int random= Util.getFaker().random().nextInt(1,10);
        if(random<8){
            throw new RuntimeException("500");
        }else if(random<10){
            throw new RuntimeException("400");
        }
    }
}
