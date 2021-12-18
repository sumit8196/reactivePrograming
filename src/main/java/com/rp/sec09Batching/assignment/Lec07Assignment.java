package com.rp.sec09Batching.assignment;

import com.rp.coureutil.Util;
import com.rp.sec05HotColdPublisher.PurchaseOrder;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Lec07Assignment {
    public static void main(String[] args) {
        Map<String, Function<Flux<PurchaseOrder>,Flux<PurchaseOrder>>> map=new HashMap<>();
        map.put("Kids",ProcessProduct.processKids());
        map.put("Automotive",ProcessProduct.processAutomotive());

        Set<String> keySet = map.keySet();
        ProcessProduct.orderStream()
                .filter(p->keySet.contains(p.getCategory()))
                .groupBy(PurchaseOrder::getCategory)//two keys
                .flatMap(gf -> map.get(gf.key()).apply(gf))
                .subscribe(Util.subscriber());
     Util.sleep(60);

    }
}
