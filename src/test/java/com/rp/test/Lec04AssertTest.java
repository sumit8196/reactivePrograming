package com.rp.test;

import com.rp.sec09Batching.assignment.BookOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec04AssertTest {
    @Test
    public void test1(){
        Mono<BookOrder> bookOrderMono=Mono.fromSupplier(()->new BookOrder());
        StepVerifier.create(bookOrderMono)
                .assertNext(bookOrder -> Assertions.assertNotNull(bookOrder.getAuther()))
                .verifyComplete();
    }
    @Test
    public void test2(){
        Mono<BookOrder> bookOrderMono=Mono.fromSupplier(()->new BookOrder())
                .delayElement(Duration.ofSeconds(3));
        StepVerifier.create(bookOrderMono)
                .assertNext(bookOrder -> Assertions.assertNotNull(bookOrder.getAuther()))
                .expectComplete()
                .verify(Duration.ofSeconds(4));
    }
}
