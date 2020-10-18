package com.sda.ForeignCurrencyAccount.shared.time;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;

@Component
public class TimeServiceImpl implements TimeService {
    @Override
    public Instant getCurrentTime() {
        return Instant.now(Clock.systemUTC());
    }
}
