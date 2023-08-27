package com.msa.rental.domain.model.entity;

import com.msa.rental.domain.model.vo.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RentItem {
    private Item item;
    private LocalDate rentDate;
    private boolean overdued;
    //반납 예정일
    private LocalDate overdueDate;

    public static RentItem createRentalItem(Item item) {
        return new
                RentItem(item, LocalDate.now(), false, LocalDate.now().plusDays(14));
    }

    public static RentItem sample() {
        return createRentalItem(Item.sample());
    }
}
