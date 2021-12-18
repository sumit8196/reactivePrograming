package com.rp.sec11SinkTelecast.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SlackRoom2 {
    private String name;
    private Sinks.Many<SlackMessage> sink;
    private Flux<SlackMessage> flux;
    public SlackRoom2(String name){
        this.name=name;
        this.sink=Sinks.many().replay().all();
        this.flux=sink.asFlux();
    }
    public void joinRoom(SlackMember member){
        System.out.println(member.getName()+".......... joined......: "+this.name);
        this.subscribe(member);
        member.setConsumer(
                msg->this.postMessage(msg,member)
        );
    }
    private void postMessage(String msg,SlackMember member){
        SlackMessage message=new SlackMessage();
        message.setMessage(msg);
        message.setSender(member.getName());
        this.sink.tryEmitNext(message);
    }
    private void subscribe(SlackMember member){
        this.flux
                .filter(msg->!msg.getSender().equals(member.getName()))
                .doOnNext(sm->sm.setReceiver(member.getName()))
                .map(SlackMessage::toString)
                .subscribe(member::receive);
    }
}
