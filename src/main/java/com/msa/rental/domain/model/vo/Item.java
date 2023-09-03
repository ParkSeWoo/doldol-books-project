package com.msa.rental.domain.model.vo;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embeddable;


@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Item {
    private int no;
    private String title;

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