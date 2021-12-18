package com.rp.sec12Context;

import com.rp.coureutil.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.Locale;

public class Lec01Context {
    public static void main(String[] args) {
        getWelcomeMessage()
                //can update context
                    .contextWrite(ctx->ctx.put("user",ctx.get("user").toString().toUpperCase(Locale.ROOT)))
                    .contextWrite(Context.of("user","sumit"))
                .subscribe(Util.subscriber());
    }
    private static Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(contextView ->{
            if(contextView.hasKey("user")){
                return Mono.just("Welcome: "+contextView.get("user"));
            }
            return Mono.error(new RuntimeException("Unathenticated"));
        });
    }
}
