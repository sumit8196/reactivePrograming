package com.rp.sec2;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FluxFromStream {
    public static void main(String[] args) {
        List<Integer> list= Arrays.asList(1,2,3,4);
        Stream<Integer> stream=list.stream();
        //stream.forEach(System.out::println);
        //stream.forEach(System.out::println); will throw error as stream can not resued.
        Flux.fromStream(stream).subscribe(Util.onNext(),Util.onError(),Util.onComplete());
       // Flux.fromStream(stream).subscribe(Util.onNext(),Util.onError(),Util.onComplete());
        //solution for these is that we can create stream form supplier as below:
        //Flux.fromStream(()->list.stream()).subscribe(Util.onNext());
        System.out.println(".............RANGE..........");
     // Flux.range(3,8).subscribe(Util.onNext());
      Flux.range(1,10)
              .log()
              .map(integer -> Util.getFaker().ancient().hero())
              .log()
              .subscribe(Util.onNext());


    }
}
