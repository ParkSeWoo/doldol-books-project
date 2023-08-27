package com.msa.rental.application.output.port;

import com.msa.rental.domain.model.RentalCard;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalCardOutputPort {
    public RentalCard loadRentalCard(String userId);
    public RentalCard save(RentalCard rentalCard);
}
