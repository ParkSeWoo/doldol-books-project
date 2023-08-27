package com.msa.rental.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCardNo {
    private String no;

    public static RentalCardNo createRentalCardNo() {
        String year = String.valueOf(LocalDate.now().getYear());
        String str = year + '-' + UUID.randomUUID();

        RentalCardNo rentalCardNo = new RentalCardNo();
        rentalCardNo.setNo(str);
        return rentalCardNo;
    }

    public static RentalCardNo sample() {
        return RentalCardNo.createRentalCardNo();
    }

    public static void main(String[] args) {
        log.info("{}", RentalCardNo.sample());
    }
}
