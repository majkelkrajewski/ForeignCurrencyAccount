package com.sda.ForeignCurrencyAccount.domain;

import com.sda.ForeignCurrencyAccount.shared.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SubAccountId implements Serializable {
    @Column(name = "sub_pesel")
    private String pesel;
    @Column(name = "sub_currency")
    private Currency currency;
}
