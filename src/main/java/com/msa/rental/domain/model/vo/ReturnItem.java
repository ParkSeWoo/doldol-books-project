package com.msa.rental.domain.model.vo;

import com.msa.rental.domain.model.entity.RentItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class ReturnItem {
    private RentItem item;
    private LocalDate returnDate;

    public static ReturnItem createRetunItem(RentItem rentItem) {
        return new ReturnItem(rentItem, LocalDate.now());
    }

    public static ReturnItem sample() {
        return ReturnItem.createRetunItem(RentItem.sample());
    }
}
