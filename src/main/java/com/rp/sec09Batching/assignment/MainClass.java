package com.rp.sec09Batching.assignment;

import com.rp.coureutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class MainClass {
    public static void main(String[] args) {
        Set<String> allowedCategory=new HashSet<String>(Arrays.asList("Science Fiction","Fantasy","Suspense/Thriller"));

        bookStream()
                .filter(bookOrder -> allowedCategory.contains(bookOrder.getCategory()))
                .buffer(Duration.ofSeconds(5))
                .map(MainClass::revenuCalculator)
                .subscribe(Util.subscriber("Generated revenue:\t"));

            Util.sleep(60);

    }
    private static Flux<BookOrder> bookStream(){
        return Flux.interval(Duration.ofMillis(200))
                .map(i-> new BookOrder());
    }
    private static RevenueReport revenuCalculator(List<BookOrder>bookOrders){
        Map<String, Double> map = bookOrders.stream()
                .collect(Collectors.groupingBy(
                        BookOrder::getCategory,
                        Collectors.summingDouble(BookOrder::getPrice)
                ));
        return new RevenueReport(map);
    }

}
