package com.rp.helper;

import com.rp.coureutil.Util;
import lombok.*;

@Data
@ToString
public class Person {
    private String name;
    private int age;
    public Person(){
        this.name= Util.getFaker().name().nameWithMiddle();
        this.age=Util.getFaker().random().nextInt(1,80);
    }
}
