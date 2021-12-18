package com.rp.helper;

import com.rp.coureutil.Util;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private int userId;
    private String name;
    public User(int userId){
        this.userId=userId;
        this.name= Util.getFaker().name().fullName();
    }


}
