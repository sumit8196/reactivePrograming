package com.rp.sec09Batching.assignment;

import com.github.javafaker.Book;
import com.rp.coureutil.Util;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BookOrder {
    private String title;
    private String auther;
    private String category;
    private double price;
    public BookOrder(){
        Book book=Util.getFaker().book();
        this.title=book.title();
        this.auther= book.author();
        this.category=book.genre();
        this.price=Double.parseDouble(Util.getFaker().commerce().price());
    }
}
