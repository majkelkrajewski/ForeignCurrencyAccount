package com.sda.ForeignCurrencyAccount.domain.request;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
@Builder
public class CreateUserAccountRequest {
    @NotNull
    private final String name;
    @NotNull
    private final String surname;
    @NotNull
    private final String pesel;
    @NotNull
    private final BigDecimal accountBallancePLN;
}
