package com.rp.test;

import com.rp.sec09Batching.assignment.BookOrder;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec05VirtualTimeTest {
    @Test
    public void test1(){
        Mono<BookOrder> bookOrderMono= Mono.fromSupplier(()->new BookOrder());
        StepVerifier.withVirtualTime(this::fluxTimeConsuming)
                .thenAwait(Duration.ofSeconds(20))
                .expectNext("1a","2a","3a","4a","5a")
                .verifyComplete();
    }
    @Test
    public void test2(){
        Mono<BookOrder> bookOrderMono= Mono.fromSupplier(()->new BookOrder());
        StepVerifier.withVirtualTime(this::fluxTimeConsuming)
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(4))
                .thenAwait(Duration.ofSeconds(20))
                .expectNext("1a","2a","3a","4a","5a")
                .verifyComplete();
    }
    private Flux<String> fluxTimeConsuming(){
        return Flux.range(1,5)
                .delayElements(Duration.ofSeconds(2))
                .map(i->i+"a");
    }
}
