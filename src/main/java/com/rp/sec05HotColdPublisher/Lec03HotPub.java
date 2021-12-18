package com.rp.sec05HotColdPublisher;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec03HotPub {
    public static void main(String[] args) {
        //share=
        Flux<String> movieStream = Flux.fromStream(() -> getMovies())
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .refCount(2);//min subscriber need so publisher will start emitting.
        movieStream.subscribe(Util.subscriber("sumit"));
        Util.sleep(2);
        movieStream.subscribe(Util.subscriber("sam"));
        test();
        Util.sleep(20);
    }
    private static Stream<String> getMovies() {
        System.out.println("Got he movies streaming request");
        return Stream.of(
                "Scene1",
                "Scene2",
                "Scene3",
                "Scene4",
                "Scene5"
        );
    }
    private static void test(){
        System.out.println("..........test.........");
        Flux<Object> movieStream = Flux.create(fluxSink -> {
            System.out.println("created");
               for(int i=0;i<5;i++){
                   fluxSink.next(i);
               }

        })
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .refCount(2);//min subscriber need so publisher will start emitting.
        movieStream.subscribe(System.out::println);
        movieStream.subscribe(System.out::println);
        movieStream.subscribe(System.out::println);
       /* movieStream.subscribe(Util.subscriber("sumit"));
        Util.sleep(2);
        movieStream.subscribe(Util.subscriber("sam"));*/
        Util.sleep(20);
    }
}
