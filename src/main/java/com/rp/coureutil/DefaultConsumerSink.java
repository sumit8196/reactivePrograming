package com.rp.coureutil;

import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class DefaultConsumerSink implements Consumer<FluxSink<Object>> {
    private FluxSink<Object> fluxSink;
   /* public DefaultConsumerSink(){

    }*/
    @Override
    public void accept(FluxSink<Object> fluxSink) {
        this.fluxSink=fluxSink;
    }
    public void produce(){
        String s = Util.getFaker().name().fullName();
        this.fluxSink.next(Thread.currentThread().getName()+" : "+s);
    }
}
