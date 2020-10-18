package com.sda.ForeignCurrencyAccount.domain.view;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class AccountView {
    private final String pesel;
    private final String name;
    private final String surname;
    private final List<SubAccountView> subAccount;
}
