package com.rp.sec08MergePublisher;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05CombineLatest {
    public static void main(String[] args) {
        Flux.combineLatest(getStrings(),getNo(),((s, integer) -> s+integer))
                .subscribe(Util.subscriber());

        Util.sleep(10);
    }
    private static Flux<String> getStrings(){
        return Flux.just("a","b","c","d")
                .delayElements(Duration.ofSeconds(1))
                ;
    }
    private static Flux<Integer> getNo(){
        return Flux.just(1,2,3)
                .delayElements(Duration.ofSeconds(3))
                ;
    }
}
