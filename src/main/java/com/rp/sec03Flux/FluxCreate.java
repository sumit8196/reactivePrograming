package com.rp.sec03Flux;

import com.rp.coureutil.DefaultConsumerSink;
import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

public class FluxCreate {
    public static void main(String[] args) {
       /* Flux<Object> objectFlux = Flux.create((fluxSink)-> {
           // fluxSink.next(1);
            while(!Util.getFaker().country().name().equalsIgnoreCase("india")){
                fluxSink.next(Util.getFaker().country().name());
            }
            fluxSink.complete();
        });*/
        //objectFlux.subscribe(Util.subscriber("ss"));
        System.out.println(Thread.currentThread().getName()+">>>>>>>");
        DefaultConsumerSink sink=new DefaultConsumerSink();
        Flux<Object> objectFlux1 = Flux.create(sink);

        objectFlux1.subscribe(Util.subscriber());
       // sink.produce();
        //here we are publishing after subscribe.
       /* Runnable runnable= sink::produce;
        for(int i=0;i<20;i++){
            new Thread(runnable).start();
        }*/
        System.out.println(Thread.currentThread().getName()+">>>>>>>");
        //Util.sleep(2);
        //whenever we are done with the emitting the data we should cancell the downstream pipelines
        //so above flux is not doing the same. we should fix the issue.

        Flux.create((fluxSink)-> {
           // fluxSink.next(1);
            String country=Util.getFaker().country().name();
           /* while(!country.equalsIgnoreCase("india")){
                System.out.println("Emiting: "+country);
                fluxSink.next(country);
            }*/
            fluxSink.next(country);
            //fluxSink.complete();
        })
                //.take(3)
                .subscribe(Util.subscriber());
    }
}
