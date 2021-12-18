package com.rp.service;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class Test2Error {
    public static void main(String[] args) {
        List<Integer> list= Arrays.asList(1,2,3,4);
         Flux.fromIterable(list)
                 .onErrorContinue((throwable,o) -> System.out.println(throwable+".1.. "+o))
                 .map(integer -> {
                     if(integer==4){
                        return integer/0;
                     }
                     return integer;
                 })
                 .onErrorContinue((throwable,o) -> System.out.println(throwable+"..2. "+o))
                .collectList()
                 .onErrorContinue((throwable,o) -> System.out.println(throwable+"..3.."+o))
                .subscribe(Util.subscriber())
         ;


    }
}
