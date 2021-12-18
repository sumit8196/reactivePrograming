package com.rp.sec09Batching;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05Group {
    public static void main(String[] args) {
        Flux.range(1,30)
                .delayElements(Duration.ofSeconds(1))
                .groupBy(i->i%2)  //key here key will be even and odd.
                .subscribe(gf->process(gf,gf.key()));

        Flux.range(1,30)
                .delayElements(Duration.ofSeconds(1))
                .groupBy(i->i%2+"a")
                .flatMap(gf -> gf)
                .subscribe();


        Util.sleep(60);


    }
    private static void process(Flux<Integer> integerFlux,int key){
        System.out.println("called>>>>>>");
        //it will be called the no of key can be possible.Here it will be two.
        integerFlux
                //.map()
                .subscribe(i-> System.out.println("Key\t:"+key+"value\t:"+i));
    }
}
