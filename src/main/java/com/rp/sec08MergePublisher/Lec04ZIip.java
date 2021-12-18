package com.rp.sec08MergePublisher;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

public class Lec04ZIip {
    public static void main(String[] args) {
        Flux.zip(getBody(),getTyres(),getEngine())
                  .doOnNext(objects -> {
                     // System.out.println("onNext: "+objects.getT1());
                  })
                .subscribe(Util.subscriber());

    }
    private  static Flux<String > getBody(){
        return Flux.range(1,5)
                .map(i->{

                    System.out.println("body"+i);
                    return "body: "+i;
                });
    }
    private  static Flux<String > getEngine(){
        return Flux.range(1,2)
                .map(i->"engine: "+i);
    }
    private  static Flux<String > getTyres(){
        return Flux.range(1,10)
                .map(i->"tyres: "+i);
    }
}
