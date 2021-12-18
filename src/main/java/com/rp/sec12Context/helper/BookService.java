package com.rp.sec12Context.helper;

import com.rp.coureutil.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BookService {
    private static Map<String,Integer> map=new HashMap<>();
    static {
        map.put("std",2);
        map.put("prime",3);
    }
    public static Mono<String> getBook(){
        return Mono.deferContextual(ctx->{
            if(ctx.get("allow")){
                return Mono.just(Util.getFaker().book().title());
            }
            return Mono.error(new RuntimeException("not-allowed"));
        })
                .contextWrite(rateLimiterContect());

    }
    private static Function<Context,Context> rateLimiterContect(){
        return ctx->{
            if(ctx.hasKey("category")){
                String category = ctx.get("category").toString();
                Integer allowedAttemts=map.get(category);
                if(allowedAttemts>0){
                    map.put(category,allowedAttemts-1);
                   return ctx.put("allow",true);
                }
            }
                return ctx.put("allow",false);
        };
    }

}
