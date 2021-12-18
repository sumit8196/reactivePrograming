package com.rp.helper;

import com.rp.coureutil.Util;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PurchaseOrder {
    private String item;
    private String price;
    private int userId;
    public PurchaseOrder(int userId) {
        this.userId = userId;
        this.item= Util.getFaker().commerce().productName();
        this.price=Util.getFaker().commerce().price();
    }
}
