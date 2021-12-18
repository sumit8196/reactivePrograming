package com.rp.sec08MergePublisher.helper;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {
    private List<String> list=new ArrayList<>();
    public Flux<String> generateNames(){
        return Flux.generate(synchronousSink -> {
            String name= Util.getFaker().name().fullName();
            System.out.println("genrated frsh\t:"+name);
            synchronousSink.next(name);
            list.add(name);
            Util.sleep(1);
        })
                .cast(String.class)
                .startWith(getFromCache());
    }
    private Flux<String> getFromCache(){
        return Flux.fromIterable(list);
    }
}
