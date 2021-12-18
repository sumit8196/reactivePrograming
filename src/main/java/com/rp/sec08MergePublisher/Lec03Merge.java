package com.rp.sec08MergePublisher;

import com.rp.coureutil.Util;
import com.rp.sec08MergePublisher.helper.AmericanAirlines;
import com.rp.sec08MergePublisher.helper.EmiratesFlights;
import com.rp.sec08MergePublisher.helper.QatarFlights;
import reactor.core.publisher.Flux;

public class Lec03Merge {
    public static void main(String[] args) {
        //merge  can publish form all the publisher concurrantly
        Flux<String> merge = Flux.merge(QatarFlights.getFlights, EmiratesFlights.getFlights, AmericanAirlines.getFlights);
        merge.subscribe(Util.subscriber());
        Util.sleep(30);
        System.out.println("........................");
        //concat will publish sequentially
        Flux<String> concat = Flux.concat(QatarFlights.getFlights, EmiratesFlights.getFlights, AmericanAirlines.getFlights);
        concat.subscribe(Util.subscriber());
        Util.sleep(30);
    }
}
