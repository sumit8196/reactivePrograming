package com.rp.sec05HotColdPublisher;

import com.rp.coureutil.Util;

public class Lec06Assignment {
    public static void main(String[] args) {
        OrderService orderService=new OrderService();
        RevenusService revenusService=new RevenusService();
        InventoryService inventoryService =new InventoryService();

        //revenus and inventory observe the order service
        orderService.orderStream().subscribe(revenusService.subscribeOrderStream());
        orderService.orderStream().subscribe(inventoryService.subscribeOrderStream());

        inventoryService.inventoryStream().subscribe(Util.subscriber("Inventory"));
        revenusService.revenuStream().subscribe(Util.subscriber("Revenue "));

        Util.sleep(60);

    }
}
