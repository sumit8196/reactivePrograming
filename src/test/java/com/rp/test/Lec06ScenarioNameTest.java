package com.rp.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class Lec06ScenarioNameTest {
    @Test
    public void test1(){
        Flux<String> just = Flux.just("a","b","c");
        StepVerifierOptions options = StepVerifierOptions.create().scenarioName("Alphabets-test");
        StepVerifier.create(just,options)
                .expectNextCount(3)
                .verifyComplete();

    }
    @Test
    public void test2(){
        Flux<String> just = Flux.just("a","b1","c");
        StepVerifierOptions options = StepVerifierOptions.create().scenarioName("Alphabets-test");
        StepVerifier.create(just)

                .expectNext("a","b","c")
                .as("a-test")
                .verifyComplete();

    }
}
