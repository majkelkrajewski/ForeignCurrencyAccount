package com.sda.ForeignCurrencyAccount.shared.external;

import com.sda.ForeignCurrencyAccount.shared.model.Currency;

public interface RateExternalService {

    RateExternalView getBidRate(Currency currency);

    RateExternalView getAskRate(Currency currency);
}
