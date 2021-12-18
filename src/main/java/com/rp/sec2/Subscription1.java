package com.rp.sec2;

import com.rp.coureutil.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class Subscription1 {
    public static void main(String[] args) {
        Flux<Integer> flux1=Flux.range(1,20);
        //custom subscription
        AtomicReference<Subscription> atomicReference=new AtomicReference<>();
        final Subscription[] subscription = null;
        flux1.log().subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("Received the subscription");
                atomicReference.set(s);
                subscription[0] =s;
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext: "+integer);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Erorr found: "+t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Completed");
            }
        });
        subscription[0].request(1);
        Util.sleep(3);
        atomicReference.get().request(3);
        Util.sleep(2);
        System.out.println("going to cancel");
        atomicReference.get().cancel();
        Util.sleep(2);
        atomicReference.get().request(3);
        Util.sleep(3);
    }
}
