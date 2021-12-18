package com.rp.sec11SinkTelecast.assignment;

import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
public class SlackRoom {
    private  Sinks.Many<Message> sink;
    private static Sinks.Many<Message> sink2=null;
    private  Flux<Message> flux;
    private static List<String> members=new ArrayList<>();
    private String name;
    public SlackRoom(String name){
        this.name=name;
        this.sink=Sinks.many().multicast().directAllOrNothing();
        this.flux=sink.asFlux();
    }
    public void joinRoom(Member member){
        this.flux
                .filter(message -> !member.getName().equals(message.getName()))
                .subscribe(message->
                    System.out.println(member.getName()+" Received:\t From :"+message.getName().toUpperCase(Locale.ROOT)
                    +"-> "+message.getMessage())
                );
    }
    /*public static Flux<Message> joinRoom(String name){
        *//*if(sink==null){
            sink=Sinks.many().multicast().directAllOrNothing();
        }
        System.out.println(name+ "\tjoined the room successfully");
        members.add(name);
        if(flux==null){
            flux=sink.asFlux();
        }
        return flux;*//*
    }*/
    public  void sendMessage(Member member,String msg){
        sink.tryEmitNext(new Message(member.getName(),msg));
    }
}
@Data
class Message{
    private String name;
    private String message;
    Message(String name,String message){
        this.name=name;
        this.message=message;
    }
}
