package com.sda.ForeignCurrencyAccount.domain.view;

import com.sda.ForeignCurrencyAccount.shared.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SubAccountView {
    private Currency currency;
    private BigDecimal value;
}
