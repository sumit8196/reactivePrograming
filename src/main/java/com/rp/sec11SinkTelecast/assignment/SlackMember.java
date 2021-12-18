package com.rp.sec11SinkTelecast.assignment;


import java.util.function.Consumer;

public class SlackMember {
    private String name;

    private Consumer<String> consumer;
    public SlackMember(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
    public void receive(String message){
        System.out.println(message);
    }
    public void says(String message){
      consumer.accept(message);
    }
    public void setConsumer(Consumer<String> consumer) {
        this.consumer = consumer;
    }


}
