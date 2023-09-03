package com.msa.rental.framework.web;

import com.msa.rental.application.usecase.CreateRentalCardUseCase;
import com.msa.rental.application.usecase.ReturnItemUserCase;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserInputDTO;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api(tags = {"대여 Controller"})
public class RentalController {
    private final CreateRentalCardUseCase createRentalCardUseCase;
    private final ReturnItemUserCase returnItemUserCase;
//    private final RentItemUseCase rentItemUseCase;
//    private final OverdueItemUserCase overdueItemUserCase;
//    private final InquiryUseCase inquiryUsecase;
//    private final ClearOverdueItemUseCase clearOverdueItemUseCase;

    @ApiOperation(value = "도서카드 생성",notes = "사용자정보 -> 도서카드정보")
    @PostMapping("/rental-card/")
    public ResponseEntity<RentalCardOutputDTO> createRentalCard(@RequestBody
                                                                        UserInputDTO userInputDTO) {
        RentalCardOutputDTO createdRentalCard =
                createRentalCardUseCase.createRentalCard(userInputDTO);
        return
                ResponseEntity.status(HttpStatus.CREATED).body(createdRentalCard);
    }

//    @ApiOperation(value = "도서카드 조회",notes = "사용자정보(id) -> 도서카드정보")
//    public void findRentalCard(){}
//    @GetMapping("/rental-card/{id}")
//    public ResponseEntity<RentalCardOutputDTO> getRentalCard(@PathVariable
//                                                                     String id) {
//        RentalCardOutputDTO rentalCardOutputDTO =
//                inquiryUsecase.getRentalCard(new UserInputDTO(id,""));
//        return ResponseEntity.ok(rentalCardOutputDTO);
//    }

    @ApiOperation(value = "반납기능", notes = "사용자정보,아이템정보1 -> 도서카드정보 ")
    @PostMapping("/RentalCard/return")
    public ResponseEntity<RentalCardOutputDTO> returnItem(@RequestBody
                                                                  UserItemInputDTO userItemInputDTO) throws Exception {
        RentalCardOutputDTO rentalCardOutputDTO =
                returnItemUserCase.returnItem(userItemInputDTO);
        return ResponseEntity.ok(rentalCardOutputDTO);
    }
}
