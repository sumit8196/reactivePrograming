package com.rp.helper;

import reactor.core.publisher.Flux;

public class UserService {
    public static Flux<User> getUser(){
        return Flux.range(1,2)
                .map(User::new);
    }
}
