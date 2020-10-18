package com.sda.ForeignCurrencyAccount.shared.external;


import lombok.Value;

@Value
public class RateExternalView {
    private String code;
    private float exchangeRate;
}
