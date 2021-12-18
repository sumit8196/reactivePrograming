package com.rp.sec05HotColdPublisher;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class ColdPublisher {
    public static void main(String[] args) {
        Flux<String> movieStream = Flux.fromStream(() -> getMovies())
                .delayElements(Duration.ofSeconds(1));
        movieStream.subscribe(Util.subscriber("sumit"));
        Util.sleep(5);
        movieStream.subscribe(Util.subscriber("sam"));
        Util.sleep(20);
    }
    private static Stream<String> getMovies(){
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
