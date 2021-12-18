package com.rp.sec11SinkTelecast.assignment;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Member {
    private String name;
    private String role;
    public Member(String name){
        this.name=name;
    }
    public void subscribe(){

    }
}
