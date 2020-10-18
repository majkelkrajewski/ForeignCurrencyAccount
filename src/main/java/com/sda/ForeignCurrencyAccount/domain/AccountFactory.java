package com.sda.ForeignCurrencyAccount.domain;

import com.sda.ForeignCurrencyAccount.domain.request.CreateUserAccountRequest;
import com.sda.ForeignCurrencyAccount.shared.model.Currency;
import com.sda.ForeignCurrencyAccount.shared.time.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class AccountFactory {
    private final TimeService timeService;

    @Transactional
    public Account create(CreateUserAccountRequest request) {
        Account account = buildWith(request);
        return account;
    }

    private Account buildWith(CreateUserAccountRequest request) {
        Instant currentTime = timeService.getCurrentTime();

        SubAccount subAccountPLN = SubAccount.builder()
                .id(new SubAccountId(request.getPesel(), Currency.PLN))
                .value(request.getAccountBallancePLN())
                .creationTime(currentTime)
                .build();

        SubAccount subAccountUSD = SubAccount.builder()
                .id(new SubAccountId(request.getPesel(), Currency.USD))
                .value(BigDecimal.ZERO)
                .creationTime(currentTime)
                .build();

        Account account = Account.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .creationTime(currentTime)
                .pesel(request.getPesel())
                .build();

        subAccountPLN.setAccount(account);
        subAccountUSD.setAccount(account);

        return account;
    }
}
