package com.rp.sec05HotColdPublisher;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec04HotPubAutoConnect {
    public static void main(String[] args) {
        //share=
        Flux<String> movieStream = Flux.fromStream(() -> getMovies())
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .autoConnect(1);//if value is zero..it will start emitting immediatly even no one subscribe it
        movieStream.subscribe(Util.subscriber("sumit"));
        Util.sleep(3);
        System.out.println("Sam is about to join");
        movieStream.subscribe(Util.subscriber("sam"));
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
}
