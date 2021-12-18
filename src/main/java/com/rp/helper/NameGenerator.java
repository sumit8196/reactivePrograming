package com.rp.helper;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class NameGenerator {
    public static void main(String[] args) {
        //System.out.println(getNames(5));
       // getNamesFlux(5).subscribe(Util.onNext());
        Flux.interval(Duration.ofSeconds(1)).subscribe(Util.onNext());
        Util.sleep(5);
    }
    public static Flux<String> getNamesFlux(int count){
        return Flux.range(1,count)
                .map((integer)->getName());
    }
    public static List<String> getNames(int count){
        List<String> list=new ArrayList<>(count);
        for(int i=0;i<count;i++){
            list.add(getName());
        }
        return list;
    }
    private static String getName(){
        Util.sleep(1);
        return Util.getFaker().name().nameWithMiddle();
    }
}



