package com.sda.ForeignCurrencyAccount.dto;

import lombok.Data;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CreateUserAccountDto {
    @NotNull(message = "Name must not be null!")
    private String name;
    @NotNull(message = "Surname must not be null!")
    private String surname;
    @PESEL(message = "Invalid pesel!")
    private String pesel;
    @DecimalMin(value = "0.0", inclusive = false, message = "Account balance must be higher than 0!")
    @Digits(integer = 10, fraction = 2)
    @NotNull(message = "Account balance must not be null!")
    private BigDecimal accountBallancePLN;

}
