package com.rp.helper;

import com.rp.coureutil.Util;
import lombok.Data;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Data
@ToString
public class Revenu {
    private List<Integer> price;
    private int orderId;
    public Revenu(int orderId){
        this.orderId=orderId;
        this.price= Arrays.asList(
                Util.getFaker().random().nextInt(10,200),
                Util.getFaker().random().nextInt(20,80),
                Util.getFaker().random().nextInt(100,200)
        );
    }
}
