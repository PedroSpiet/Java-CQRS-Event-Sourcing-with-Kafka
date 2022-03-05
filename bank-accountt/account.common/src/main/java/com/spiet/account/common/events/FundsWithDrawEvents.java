package com.spiet.account.common.events;

import com.spiet.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundsWithDrawEvents extends BaseEvent {
    private double amount;
}
