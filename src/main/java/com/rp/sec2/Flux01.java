package com.rp.sec2;

import com.github.javafaker.Faker;
import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.stream.Stream;

public class Flux01 {
    public static void main(String[] args) {
        Flux<Integer> flux1=Flux.just(1,2);
        Flux<Integer> flux2=Flux.empty();
        Flux<Object> flux3=Flux.just(1,2,"2","a","edwe", Util.getFaker().address().city());
       // flux1.subscribe(Util.onNext(),Util.onError(),Util.onComplete());
        flux3.subscribe(Util.onNext(),Util.onError(),Util.onComplete());
        flux3.subscribe((s)-> System.out.println("subscibe 2nd itme "+s));
        Stream<Integer> stream=Stream.of(1,2);
        //<String> stream2=Stream.of("1","2");
        //stream.forEach(System.out::println);
        //stream.forEach(System.out::println);//will throw error because it will be processed only once unlike publisher
        System.out.println("....");
        Flux<Integer> integerFlux=Flux.just(1,2,3,4,5);
        integerFlux.filter(i->i%2==0).subscribe(Util.onNext());
        integerFlux.filter(i->i%2==1).subscribe(Util.onNext());
        Flux<String> flux4=Flux.fromIterable(Arrays.asList("a","b","c","d"));
        flux4.subscribe(Util.onNext());
        Integer[] array={1,2,3,4,5};
        Flux<Integer> flux5=Flux.fromArray(array);
        flux5.subscribe(Util.onNext());
    }
}
