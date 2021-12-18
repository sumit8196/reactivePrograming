package com.rp.sec05HotColdPublisher;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RevenusService {
    private Map<String, Double> revenueDb =new HashMap<>();
    public RevenusService() {
        revenueDb.put("Kids",0.0);
        revenueDb.put("Automotive",0.0);
    }
    public Consumer<PurchaseOrder> subscribeOrderStream(){
        return purchaseOrder->revenueDb.computeIfPresent(purchaseOrder.getCategory(),(K,V)-> V+purchaseOrder.getPrice());
    }
    public Flux<String> revenuStream(){
        return Flux.interval(Duration.ofSeconds(2))
                .map(i->revenueDb.toString());
    }
}
