package com.msa.rental.application.input.port;

import com.msa.rental.application.output.port.RentalCardOutputPort;
import com.msa.rental.application.usecase.CreateRentalCardUseCase;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateRentalCardInputPort implements CreateRentalCardUseCase {
    private final RentalCardOutputPort rentalCardOutputPort;
    
    @Override
    public RentalCardOutputDTO createRentalCard(UserInputDTO userInputDTO) {
        return null;
    }
}
