package com.sda.ForeignCurrencyAccount.api;

import com.sda.ForeignCurrencyAccount.domain.AccountFacade;
import com.sda.ForeignCurrencyAccount.domain.view.AccountView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class FindUserAccountController {

    private final AccountFacade facade;

    @GetMapping("/{pesel}")
    public ResponseEntity<AccountView> findAccount(@PathVariable String pesel) {
        AccountView accountView = facade.find(pesel);
        return ResponseEntity.ok(accountView);
    }
}
