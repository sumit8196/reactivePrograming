package com.rp.sec12Context;

import com.rp.coureutil.Util;
import com.rp.sec12Context.helper.BookService;
import com.rp.sec12Context.helper.UserService;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec02RateLimiterDemo {
    public static void main(String[] args) {
        BookService bookService=new BookService();
        UserService userService=new UserService();
        Mono<String> mono = BookService.getBook()
                .contextWrite(ctx -> UserService.userCategoryContext().apply(ctx));
        mono
                .repeat(3)
                .contextWrite(Context.of("user","sam"))
                .subscribe(Util.subscriber("..."));

    }
}
