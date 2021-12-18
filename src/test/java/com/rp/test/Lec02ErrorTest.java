package com.rp.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec02ErrorTest {
    @Test
    public void test1(){
        Flux<Integer> just = Flux.just(1, 2, 3);
        Flux<Integer> error = Flux.error(new RuntimeException("error"));
        StepVerifier.create(just.concatWith(error))
                .expectNext(1,2,3)
                .verifyError();

    }
    @Test
    public void test2(){
        Flux<Integer> just = Flux.just(1, 2, 3);
        Flux<Integer> error = Flux.error(new RuntimeException("error"));
        StepVerifier.create(just.concatWith(error))
                .expectNext(1,2,3)
                .verifyErrorMessage("error")
                ;

    }
    @Test
    public void test3(){
        Flux<Integer> just = Flux.just(1, 2, 3);
        Flux<Integer> error = Flux.error(new RuntimeException("error"));
        StepVerifier.create(just.concatWith(error))
                .expectNext(1,2,3)
                .verifyError(RuntimeException.class);

    }
}
