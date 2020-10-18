package com.sda.ForeignCurrencyAccount.domain;

import com.sda.ForeignCurrencyAccount.domain.request.CreateUserAccountRequest;
import com.sda.ForeignCurrencyAccount.domain.request.ExchangeCurrencyRequest;
import com.sda.ForeignCurrencyAccount.domain.view.AccountView;
import com.sda.ForeignCurrencyAccount.shared.exceptions.BadRequestException;
import com.sda.ForeignCurrencyAccount.shared.exceptions.ResourceAlreadyExistsException;
import com.sda.ForeignCurrencyAccount.shared.exceptions.ResourceNotExistsException;
import com.sda.ForeignCurrencyAccount.shared.external.RateExternalService;
import com.sda.ForeignCurrencyAccount.shared.external.RateExternalView;
import com.sda.ForeignCurrencyAccount.shared.model.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class AccountFacade {

    private final AccountFactory factory;

    private final AccountRepository repository;

    private final RateExternalService rateExternalService;

    @Transactional
    public AccountView create(CreateUserAccountRequest request) {
        assertAccountWithGivenPeselDoesNotExist(request.getPesel());
        Account account = factory.create(request);
        repository.save(account);
        return account.toView();
    }

    private void assertAccountWithGivenPeselDoesNotExist(String pesel) {
        boolean exist = repository.existsAccountByPesel(pesel);
        if (exist) {
            throw new ResourceAlreadyExistsException("Account with given pesel already exists!");
        }
    }

    @Transactional(readOnly = true)
    public AccountView find(String pesel) {
        return repository.findAccountByPesel(pesel)
                .map(Account::toView)
                .orElseThrow(() -> new ResourceNotExistsException("Account with given pesel not found!"));
    }

    @Transactional
    public AccountView exchange(ExchangeCurrencyRequest request) {
        if (request.getCurrencyFrom().equals(request.getCurrencyTo())) {
            throw new BadRequestException("Curriencies should be different!");
        }
        Account account = repository.findAccountByPesel(request.getPesel())
                .orElseThrow(() -> new ResourceNotExistsException("Account with given pesel not found!"));
        BigDecimal valueCurrencyFrom = account.getSubAccountValueByCurrency(request.getCurrencyFrom());
        if (valueCurrencyFrom.compareTo(request.getAmount()) < 0) {
            throw new BadRequestException(String.format("Amount %s %s increased account funds!",
                    request.getAmount(), request.getCurrencyFrom()));
        }

        if (request.getCurrencyFrom().equals(Currency.PLN)) {
            exchangeFromPLN(account, request);
        } else {
            exchangeToPLN(account, request);
        }

        repository.save(account);
        return account.toView();

    }

    private void exchangeFromPLN(Account account, ExchangeCurrencyRequest request) {
        RateExternalView askRate = rateExternalService.getAskRate(request.getCurrencyTo());

        BigDecimal amount = request.getAmount().divide(BigDecimal.valueOf(askRate.getExchangeRate()), 2, RoundingMode.DOWN);
        account.addValue(amount, request.getCurrencyTo());
        account.reduceValue(request.getAmount(), request.getCurrencyFrom());
    }

    private void exchangeToPLN(Account account, ExchangeCurrencyRequest request) {
        RateExternalView bidRate = rateExternalService.getBidRate(request.getCurrencyFrom());
        BigDecimal amount = request.getAmount().multiply(BigDecimal.valueOf(bidRate.getExchangeRate())).setScale(2, RoundingMode.DOWN);
        account.addValue(amount, request.getCurrencyTo());
        account.reduceValue(request.getAmount(), request.getCurrencyFrom());
    }

}
