package com.rp.sec04Operators;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

public class LimitRate {
    public static void main(String[] args) {
        Flux.range(1,10000)
                .log()
                .limitRate(100,50) //by default it is 75 but we can adjust also
                                                   //if you want to drain all data than only you will reques than use
                                                    //.limitRate(100,0) ->It is confusing.
                .subscribe(Util.subscriber());


    }
}
