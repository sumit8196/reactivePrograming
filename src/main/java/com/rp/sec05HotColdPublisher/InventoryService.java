package com.rp.sec05HotColdPublisher;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class InventoryService {
    private Map<String, Integer> db =new HashMap<>();
    public InventoryService() {
        db.put("Kids",100);
        db.put("Automotive",100);
    }
    public Consumer<PurchaseOrder> subscribeOrderStream(){
        return purchaseOrder->db.computeIfPresent(purchaseOrder.getCategory(),(K,V)-> V-purchaseOrder.getQuantity());
    }
    public Flux<String> inventoryStream(){
        return Flux.interval(Duration.ofSeconds(2))
                .map(i->db.toString());
    }
}
