package com.sda.ForeignCurrencyAccount.domain;

import com.sda.ForeignCurrencyAccount.shared.model.Currency;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubAccount {
    @EmbeddedId
    private SubAccountId id;
    @NotNull
    private BigDecimal value;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pesel", nullable = false)
    private Account account;
    @NotNull
    private Instant creationTime;

    public Currency getCurrency() {
        return id.getCurrency();
    }

    void add(BigDecimal v) {
        value = value.add(v).setScale(2, RoundingMode.DOWN);
    }

    void reduce(BigDecimal v) {
        value = value.subtract(v).setScale(2, RoundingMode.DOWN);
    }
}
