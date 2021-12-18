package com.rp.sec08MergePublisher;

import com.rp.coureutil.Util;
import com.rp.sec08MergePublisher.helper.NameGenerator;

public class Lec01StartWith {
    public static void main(String[] args) {
        NameGenerator nameGenerator=new NameGenerator();
        nameGenerator.generateNames()
                .take(2)
                .subscribe(Util.subscriber());
        nameGenerator.generateNames()
                .take(3)
                .subscribe(Util.subscriber());

        //he might get from cache or from freshly created
        nameGenerator.generateNames()
                .filter(n->n.startsWith("A"))
                .take(1)
                .subscribe(Util.subscriber());
    }

}
