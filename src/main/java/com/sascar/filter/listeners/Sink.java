package com.sascar.filter.listeners;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Sink {
    String INPUT = "positions";

    @Input("positions")
    SubscribableChannel input();
}