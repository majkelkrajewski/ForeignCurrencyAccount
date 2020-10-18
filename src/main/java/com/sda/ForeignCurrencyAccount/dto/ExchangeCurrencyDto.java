package com.sda.ForeignCurrencyAccount.dto;

import com.sda.ForeignCurrencyAccount.shared.model.Currency;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ExchangeCurrencyDto {
    @NotNull
    private Currency currencyFrom;
    @NotNull
    private Currency currencyTo;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @DecimalMin(value = "0.0", inclusive = false, message = "Value must be higher than 0!")
    private BigDecimal amount;
}
