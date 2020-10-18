package com.sda.ForeignCurrencyAccount.api;

import com.sda.ForeignCurrencyAccount.domain.AccountFacade;
import com.sda.ForeignCurrencyAccount.domain.request.ExchangeCurrencyRequest;
import com.sda.ForeignCurrencyAccount.domain.view.AccountView;
import com.sda.ForeignCurrencyAccount.dto.ExchangeCurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class ExchangeCurrencyController {

    private final AccountFacade facade;

    @PostMapping("/{pesel}/exchange")
    public ResponseEntity<AccountView> exchange(@Valid @RequestBody ExchangeCurrencyDto dto, @PathVariable String pesel) {
        ExchangeCurrencyRequest request = ExchangeCurrencyRequest.builder()
                .pesel(pesel)
                .amount(dto.getAmount())
                .currencyFrom(dto.getCurrencyFrom())
                .currencyTo(dto.getCurrencyTo())
                .build();

        AccountView accountView = facade.exchange(request);
        return new ResponseEntity<>(accountView, HttpStatus.ACCEPTED);
    }
}
