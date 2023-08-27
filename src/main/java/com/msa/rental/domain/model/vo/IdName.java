package com.msa.rental.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IdName {
    private String id;
    private String name;

    public static IdName sample() {
        return new IdName("scant", "hanjeongheon");
    }

    public static void main(String[] args) {
        log.info("{}", sample().toString());
    }
}
