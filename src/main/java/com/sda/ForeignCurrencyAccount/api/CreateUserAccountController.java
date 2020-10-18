package com.sda.ForeignCurrencyAccount.api;

import com.sda.ForeignCurrencyAccount.domain.Account;
import com.sda.ForeignCurrencyAccount.domain.AccountFacade;
import com.sda.ForeignCurrencyAccount.domain.request.CreateUserAccountRequest;
import com.sda.ForeignCurrencyAccount.domain.view.AccountView;
import com.sda.ForeignCurrencyAccount.dto.CreateUserAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class CreateUserAccountController {

    private final AccountFacade facade;

    @PostMapping
    public ResponseEntity<AccountView> create(@Valid @RequestBody CreateUserAccountDto dto) {
        CreateUserAccountRequest request = from(dto);
        AccountView account = facade.create(request);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }


    private CreateUserAccountRequest from(CreateUserAccountDto dto) {
        return CreateUserAccountRequest.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .accountBallancePLN(dto.getAccountBallancePLN())
                .pesel(dto.getPesel())
                .build();
    }

}
