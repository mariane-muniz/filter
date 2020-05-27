package com.sascar.filter.listeners;

import com.sascar.filter.dto.ConfigurationFilter;
import com.sascar.filter.dto.Position;
import com.sascar.filter.filters.AbstractFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@EnableBinding(Sink.class)
@RequiredArgsConstructor
public class EventListener {
    private final List<ConfigurationFilter> filterConfigs;

    @StreamListener(Sink.INPUT)
    public void consume(Position position) {
        filterConfigs.parallelStream().forEach(configurationFilter -> {
            Optional<AbstractFilter> invalidFilter = configurationFilter.getFilters()
                    .stream().filter(filter -> !filter.validate(position)).findFirst();
            if (invalidFilter.isEmpty())log.info("Send message: " + position.toString());
        });
    }
}