package com.rp.sec05HotColdPublisher;

import com.rp.coureutil.Util;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PurchaseOrder {
    private String item;
    private double price;
    private String category;
    private int quantity;
    public PurchaseOrder() {
        this.item= Util.getFaker().commerce().productName();
        this.price= Double.parseDouble(Util.getFaker().commerce().price());
        this.category=Util.getFaker().commerce().department();
        this.quantity=Util.getFaker().random().nextInt(1,10);
    }
}
