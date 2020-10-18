package com.sda.ForeignCurrencyAccount.domain;

import com.sda.ForeignCurrencyAccount.domain.view.AccountView;
import com.sda.ForeignCurrencyAccount.domain.view.SubAccountView;
import com.sda.ForeignCurrencyAccount.shared.model.Currency;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @Column(nullable = false, unique = true, name = "pesel")
    private String pesel;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account", cascade = CascadeType.ALL)
    private List<SubAccount> subAccount;
    @NotNull
    private Instant creationTime;
    @Version
    private Integer version;

    public BigDecimal getSubAccountValueByCurrency(Currency currency) {
        return subAccount.stream()
                .filter(sa -> sa.getCurrency().equals(currency))
                .map(SubAccount::getValue)
                .findAny()
                .orElseGet(() -> BigDecimal.ZERO);
    }

    public void addValue(BigDecimal value, Currency currency) {
        subAccount.stream()
                .filter(sa -> sa.getCurrency().equals(currency))
                .forEach(sa -> sa.add(value));
    }

    public void reduceValue(BigDecimal value, Currency currency) {
        subAccount.stream()
                .filter(sa -> sa.getCurrency().equals(currency))
                .forEach(sa -> sa.reduce(value));
    }

    public AccountView toView() {
        List<SubAccountView> subAccountViews = subAccount.stream()
                .map(s -> new SubAccountView(s.getCurrency(), s.getValue()))
                .collect(Collectors.toList());

        return AccountView.builder()
                .name(name)
                .pesel(pesel)
                .surname(surname)
                .subAccount(subAccountViews)
                .build();
    }
}
