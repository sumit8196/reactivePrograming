package com.rp.sec;

import java.util.stream.Stream;

public class Stream1 {
    public  static void main(String[] arg){
        Stream<Integer> stream=Stream.of(1)
                                .map(num->{
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    return num*2;
                                });
        //System.out.println(stream); stream is lazy so it will not return anything
        stream.forEach(System.out::println);

    }
}
