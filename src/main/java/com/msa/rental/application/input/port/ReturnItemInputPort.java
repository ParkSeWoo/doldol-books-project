package com.msa.rental.application.input.port;

import com.msa.rental.application.output.port.RentalCardOutputPort;
import com.msa.rental.application.usecase.ReturnItemUserCase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
@Transactional
public class ReturnItemInputPort implements ReturnItemUserCase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalCardOutputDTO returnItem(UserItemInputDTO returnDto) throws Exception {
        // OutputPort를 사용해서 rental를 검색한 후 없으면 에러 있으면 도서 반납 / OutputPort에 저장
        RentalCard rental =
                rentalCardOutputPort.loadRentalCard(returnDto.getUserId());

        if (rental == null) {
            new IllegalArgumentException("해당 카드가 존재하지 않습니다.");
        }

        rental.returnItem(new
                Item(returnDto.getItemId(), returnDto.getItemTitle()), LocalDate.now());
        rentalCardOutputPort.save(rental);
        return RentalCardOutputDTO.mapToDTO(rental);
    }
}
