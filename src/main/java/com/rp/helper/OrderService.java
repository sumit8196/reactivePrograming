package com.rp.helper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderService {
    private static Map<Integer, List<PurchaseOrder>> db=new HashMap<>();//map of userId and list of purchase order
    static {
        List<PurchaseOrder> list1= Arrays.asList(
                new PurchaseOrder(1),
                new PurchaseOrder(1),
                new PurchaseOrder(1)
        );
        List<PurchaseOrder> list2= Arrays.asList(
                new PurchaseOrder(2),
                new PurchaseOrder(2),
                new PurchaseOrder(2)
        );
        db.put(1,list1);
        db.put(2,list2);
    }
    public static Flux<PurchaseOrder> getOrder(int userId){
        return Flux.fromIterable(db.get(userId));
    }
    public static Flux<PurchaseOrder> getOrder2(int userId){
        return Flux.create(sink->{
            db.get(userId).forEach(sink::next);
            sink.complete();
        });
    }
    public static Flux<PurchaseOrder> getOrderWithDelay(int userId){
        return Flux.create((FluxSink<PurchaseOrder> sink)->{
            db.get(userId).forEach(sink::next);
            sink.complete();
        }).delayElements(Duration.ofSeconds(1));
    }
    public static Flux<PurchaseOrder> getOrder(){
        return Flux.fromIterable(
                db.entrySet().stream()
                        //.map(integerListEntry -> integerListEntry.getValue())
                        .flatMap(purchaseOrders -> purchaseOrders.getValue().stream())
                        .collect(Collectors.toList())
        );
    }
}
