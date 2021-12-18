package com.rp.sec;

import com.rp.coureutil.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class MonoFromFuture {
    public static void main(String[] args) {
        Mono.fromFuture(MonoFromFuture::getName)
                .subscribe(Util.onNext());

        Util.sleep(1);
        System.out.println("..");
        Mono.fromRunnable(()-> System.out.println(Util.getFaker().name().fullName()) ).subscribe(Util.onNext(),Util.onError(),Util.onComplete());
    }
    private static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(()-> Util.getFaker().name().fullName());
    }
}
