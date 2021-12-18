package com.rp.sec06PublishOnSubscribeON;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class Lec06Parallel {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
       Flux.range(1,20)
               .parallel(2)
               .runOn(Schedulers.parallel())
               .doOnNext(i->printThreadName("next: "+i))
               //.sequential() it will make things sequential by thread wise
               .subscribe(list::add);
        Util.sleep(5);
        System.out.println(list.size());
    }
    private static void printThreadName(String msg){
        System.out.println(msg+"\tThread:\t."+Thread.currentThread().getName());
    }
}
