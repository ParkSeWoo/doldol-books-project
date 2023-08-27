package com.msa.rental.domain.model.vo;


import lombok.*;


@NoArgsConstructor
public class Item {
    private int no;
    private String title;

    public Item(int no, String title) {
        this.no = no;
        this.title = title;
    }

    public int getNo() {
        return this.no;
    }

    public String getTitle() {
        return this.title;
    }

    public static Item sample(){
        return new Item(10, "노인과바다");
    }
}