package com.rp.sec04Operators;

import com.rp.coureutil.Util;
import com.rp.helper.OrderService;
import com.rp.helper.UserService;

public class LecFlatMap {
    //
    public static void main(String[] args) {
        //if i use map than it will emit flux.
        UserService.getUser()
                .map(user->OrderService.getOrder(user.getUserId()))
                .subscribe(Util.subscriber());
        //flat map..extracting the information and publishing it
        System.out.println(".....flat map........");
        UserService.getUser()
                .flatMap(user->OrderService.getOrderWithDelay(user.getUserId()))
                .subscribe(Util.subscriber());
        Util.sleep(10);
    }
}
