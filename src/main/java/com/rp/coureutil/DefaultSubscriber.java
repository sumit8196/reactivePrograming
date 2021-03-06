package com.rp.coureutil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DefaultSubscriber implements Subscriber<Object> {
    private Subscription subscription;
    private String name;
    public DefaultSubscriber(String name){
        this.name=name;
    }
    public DefaultSubscriber(){
    }
    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Object t) {
        System.out.println(name+" Received: "+t);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println(name+" Error: "+t.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println(name+" Completed: ");
    }
}
