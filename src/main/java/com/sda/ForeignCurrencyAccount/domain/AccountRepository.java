package com.sda.ForeignCurrencyAccount.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    boolean existsAccountByPesel(String pesel);

    Optional<Account> findAccountByPesel(String pesel);
}
