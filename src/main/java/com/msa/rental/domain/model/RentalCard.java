package com.msa.rental.domain.model;

import com.msa.rental.domain.model.entity.RentItem;
import com.msa.rental.domain.model.vo.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RentalCard {
    private RentalCardNo rentalCardNo;
    private IdName idName;
    private RentStatus rentStatus;
    private LateFee totalLateFee;
    private List<RentItem> rentItemList = new ArrayList<RentItem>();
    private List<ReturnItem> retrunItemList = new ArrayList<ReturnItem>();
}
