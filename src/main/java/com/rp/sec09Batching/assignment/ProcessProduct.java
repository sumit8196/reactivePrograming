package com.rp.sec09Batching.assignment;

import com.rp.coureutil.Util;
import com.rp.sec05HotColdPublisher.PurchaseOrder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class ProcessProduct {
    private static List<String> allowedCategory= Arrays.asList("Kids","Automotive");
    private static double kidsDiscount=0.5;
    private static double tax=0.1;
    public static void main(String[] args) {

            groupOrder()
                    .flatMap(ProcessProduct::processOrder)
                    .subscribe(Util.subscriber("Processed order is:\t"));







      Util.sleep(30);



    }
    public static Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> processAutomotive(){
        return flux->flux
                .doOnNext(p->p.setPrice(1.1*p.getPrice()))
                .doOnNext(p->p.setItem("{{: "+p.getItem()+":}}"))
                ;
    }
    public static Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> processKids(){
        return flux->flux
                .doOnNext(p->p.setPrice(0.5*p.getPrice()))
                .flatMap(p->Flux.concat(Flux.just(p),getFreeKidsOrder()))

                ;
    }
    private static Flux<PurchaseOrder> processOrder(GroupedFlux<Object,PurchaseOrder> flux){
       return flux
                .flatMap(purchaseOrder -> {
                    System.out.println(">>>>>>>>>."+purchaseOrder);
                    double price=purchaseOrder.getPrice();
                    if(purchaseOrder.getCategory().equals("Kids")){
                        purchaseOrder.setPrice(price*kidsDiscount);
                        return Flux.just(purchaseOrder).concatWith(Flux.just(freeOrder()));
                    }else {
                        purchaseOrder.setPrice(price+price*tax);
                        return Flux.just(purchaseOrder);
                    }
                });
    }
    private static Flux<GroupedFlux<Object,PurchaseOrder>> groupOrder(){
       return  orderStream()
                .filter(order->allowedCategory.contains(order.getCategory()))
                .groupBy(order->order.getCategory())

               ;
    }
    public static Flux<PurchaseOrder> orderStream(){
       return Flux.interval(Duration.ofSeconds(1))
                .map(i->new PurchaseOrder());
    }
    private static PurchaseOrder freeOrder(){
        PurchaseOrder purchaseOrder= new PurchaseOrder();
        purchaseOrder.setCategory("Free");
        purchaseOrder.setPrice(0.0);
        return purchaseOrder;
    }
    private static Mono<PurchaseOrder> getFreeKidsOrder(){
        return Mono.fromSupplier(()->{
            PurchaseOrder purchaseOrder=new PurchaseOrder();
            purchaseOrder.setPrice(0.0);
            purchaseOrder.setItem("Free Product: "+purchaseOrder.getItem());
            purchaseOrder.setCategory("Kids");
            return purchaseOrder;
        });

    }
}
