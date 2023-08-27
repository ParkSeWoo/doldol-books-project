package com.msa.rental.application.input.port;

import com.msa.rental.application.output.port.RentalCardOutputPort;
import com.msa.rental.application.usecase.RentalItemUseCase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.IdName;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RentalItemInputPort implements RentalItemUseCase {
    private final RentalCardOutputPort rentalCardOutputPort;
    @Override
    public RentalCardOutputDTO rentItem(UserItemInputDTO rental) throws Exception {
        RentalCard userRentalCard =
                rentalCardOutputPort.loadRentalCard(rental.getUserId());

        if (userRentalCard == null) {
            userRentalCard = RentalCard.createRentalCard(new
                    IdName(rental.getUserId(), rental.getUserNm()));
        }
        userRentalCard.rentalItem(new Item(rental.getItemId(),
                rental.getItemTitle()));
        userRentalCard = rentalCardOutputPort.save(userRentalCard);
        return RentalCardOutputDTO.mapToDTO(userRentalCard);
    }
}
