package com.sda.ForeignCurrencyAccount.shared.external;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
class RateResponse {
    private String table;
    private String currency;
    private String code;
    private List<Table> rates;
}
