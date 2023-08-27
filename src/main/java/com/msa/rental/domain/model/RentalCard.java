package com.msa.rental.domain.model;

import com.msa.rental.domain.model.entity.RentItem;
import com.msa.rental.domain.model.vo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RentalCard {
    private RentalCardNo rentalCardNo;
    private IdName member;
    private RentalStatus rentalStatus;
    private LateFee totalLateFee;
    private List<RentItem> rentalItemList = new ArrayList<RentItem>();
    private List<ReturnItem> returnItemList = new ArrayList<ReturnItem>();

    public static RentalCard sample(){
        RentalCard rentalCard = new RentalCard();
        rentalCard.setRentalCardNo(RentalCardNo.createRentalCardNo());
        rentalCard.setMember(IdName.sample());
        rentalCard.setRentalStatus(RentalStatus.RENT_AVAILABLE);
        rentalCard.setTotalLateFee(LateFee.sample());
        return rentalCard;
    }

    private void addRentalItem(RentItem rentalItem){
        this.rentalItemList.add(rentalItem);
    }

    private void removeRentalItem(RentItem rentalItem) {
        this.rentalItemList.remove(rentalItem);
    }

    private void addReturnItem(ReturnItem returnItem) {
        this.returnItemList.add(returnItem);
    }

    public static RentalCard createRentalCard(IdName idName) {
        RentalCard rentalCard = new RentalCard();
        rentalCard.setRentalCardNo(RentalCardNo.createRentalCardNo());
        rentalCard.setMember(IdName.sample());
        rentalCard.setRentalStatus(RentalStatus.RENT_AVAILABLE);
        rentalCard.setTotalLateFee(LateFee.creatLateFee());
        return rentalCard;
    }

    public RentalCard rentalItem(Item item) throws Exception {
        checkRentalAvailable();
        this.addRentalItem(RentItem.createRentalItem(item));
        return this;
    }

    private void checkRentalAvailable() throws Exception {
        if (this.rentalStatus == RentalStatus.RENT_UNAVAILABLE) throw new
                IllegalAccessException("대여불가상태입니다.");
        if (this.rentalItemList.size() > 5) throw new Exception("이미 5권을 대여했습니다.");
    }

    public RentalCard returnItem(Item item, LocalDate retrunDate){
        RentItem rentalItem = this.rentalItemList.stream().filter(i ->
                i.getItem().equals(item)).findFirst().get();
        calculateLateFee(rentalItem,retrunDate);
        this.addReturnItem(ReturnItem.createRetunItem(rentalItem));
        this.removeRentalItem(rentalItem);
        return this;
    }

    private void calculateLateFee(RentItem item, LocalDate returnDate){
        if (returnDate.compareTo(item.getOverdueDate())>0) {
            Integer point = 0;
            point = Period.between(item.getOverdueDate(), returnDate).getDays() *
                    10;
            LateFee addedPoint = this.totalLateFee.addPoint(point);
            this.setTotalLateFee(addedPoint);
        }
    }

    public RentalCard overdueItem(Item item){
        RentItem rentedItem = this.rentalItemList.stream().filter(i ->
                i.getItem().equals(item)).findFirst().get();
        rentedItem.setOverdued(true);
        this.rentalStatus = RentalStatus.RENT_UNAVAILABLE;
        // 연체 억지로 만들기 - 실제로는 필요없는 코드
        rentedItem.setOverdueDate(LocalDate.now().minusDays(1));
        return this;
    }

    public Integer makeAvailableRental(Integer point) throws Exception {
        // 연체비 계산하기
        if (this.rentalItemList.size() != 0) throw new
                IllegalArgumentException("모든 도서가 반납되어야 정지를 해제 할 수 있습니다.");
        if (this.getTotalLateFee().getPoint() != point) throw new
                IllegalArgumentException("해당 포인트로 연체를 해제할 수 없습니다.");
        this.setTotalLateFee(totalLateFee.removePoint(point));
        if (this.getTotalLateFee().getPoint() == 0)
        {
            this.rentalStatus = RentalStatus.RENT_AVAILABLE;
        }
        return this.getTotalLateFee().getPoint();
    }

}
