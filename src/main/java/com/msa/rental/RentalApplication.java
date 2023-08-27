package com.msa.rental;

import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.Item;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.stream.Collectors;

@SpringBootApplication
public class RentalApplication {

    public static void main(String[] args) throws Exception {
        domainTest();
        SpringApplication.run(RentalApplication.class, args);
    }

    public static void domainTest() throws Exception {
        System.out.println("◉◉◉◉◉◉◉◉◉ 도메인 모델 테스트 진행 ◉◉◉◉◉◉◉◉◉");
        System.out.println("▶▶▶ 도서카드 생성");
        RentalCard sampleCard = RentalCard.sample();
        showcardStatus(sampleCard);
        Item sample1 = new Item(1, "TEST");
        Item sample2 = new Item(1, "TEST2");

        System.out.println("▶▶▶ 도서명: " + sample1.getTitle() + " 대여됨");
        sampleCard.rentalItem(sample1);
        System.out.println("▶▶▶ 도서명: " + sample2.getTitle() + " 대여됨");
        sampleCard.rentalItem(sample2);

        showcardStatus(sampleCard);
        System.out.println("▶▶▶ 도서명: " + sample1.getTitle() + " 반납됨");
        sampleCard.returnItem(sample1, LocalDate.now());
        showcardStatus(sampleCard);
        System.out.println("▶▶▶ 도서명: " + sample2.getTitle() + " 강제 연체");
        sampleCard.overdueItem(sample2);
        showcardStatus(sampleCard);
        //sampleCard.calculateLateFee();
        System.out.println("▶▶▶ 도서명: " + sample2.getTitle() + " 반납됨");
        sampleCard.returnItem(sample2, LocalDate.now());
        showcardStatus(sampleCard);
        System.out.println("▶▶▶ 정지해제처리");
        Integer minusPoint =
                sampleCard.makeAvailableRental(sampleCard.getTotalLateFee().getPoint());
        System.out.println("▶▶▶ 현재 남은 연체료는 " + minusPoint);
    }

    private static void showcardStatus(RentalCard sampleCard) {

        System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
        System.out.println("--- [ " +
                sampleCard.getMember().getName() + " 도서카드 ");
        System.out.println("--- [ 대여도서 연체상태 : " +
                sampleCard.getRentalItemList().stream().map(m -> m.isOverdued()).collect(Collectors.toList()));
        System.out.println("--- [ 총연체료: " +
                sampleCard.getTotalLateFee().getPoint());
        System.out.println("--- [ 대여가능여부: " +
                sampleCard.getRentalStatus().toString());
        System.out.println("--- [ 대여 목록");
        System.out.println("--- [ " +
                sampleCard.getRentalItemList().stream().map(m -> m.getItem().getTitle()).collect(Collectors.toList()));
        System.out.println("--- [ 반납목록");
        System.out.println("--- [ " + sampleCard.getRetrunItemList().stream().map(m -> m.getItem().getItem().getTitle()).collect(Collectors.toList()));

        System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
    }
}
