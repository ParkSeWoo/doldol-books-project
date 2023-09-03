package com.msa.rental.framework.jpaadapter;

import com.msa.rental.application.output.port.RentalCardOutputPort;
import com.msa.rental.domain.model.RentalCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RentalCardJpaAdapter implements RentalCardOutputPort {
    private final RentalCardRepository rentalCardRepository;

    @Override
    public RentalCard loadRentalCard(String userId) {
        return rentalCardRepository.findByMemberId(userId).get();
    }

    @Override
    public RentalCard save(RentalCard rentalCard) {
        return rentalCardRepository.save(rentalCard);
    }
}
