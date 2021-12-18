package com.rp.coureutil;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;

import java.util.function.Consumer;

public class Util implements Runnable {
    private static final Faker FAKER=Faker.instance();
    public static Consumer<Object> onNext(){
        return o -> System.out.println("Received: "+o);
    }
    public static Consumer<Throwable> onError(){
        return e -> System.out.println("Error: "+e.getMessage());
    }
    public static Runnable onComplete(){
        return ()->System.out.println("Completed ");
    }
    public static Faker getFaker(){
        return FAKER;
    }
    public static void sleep(int sec){
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void sleepMili(int mili){
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static Subscriber subscriber(){
        return new DefaultSubscriber();
    }
    public static Subscriber subscriber(String name){
        return new DefaultSubscriber(name);
    }
    @Override
    public void run() {
        System.out.println("Completed ");
    }
}
