package com.sda.ForeignCurrencyAccount.domain.request;

import com.sda.ForeignCurrencyAccount.shared.model.Currency;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
@Builder
public class ExchangeCurrencyRequest {
    @NotNull
    private final String pesel;
    @NotNull
    private Currency currencyFrom;
    @NotNull
    private Currency currencyTo;
    @NotNull
    private BigDecimal amount;
}
