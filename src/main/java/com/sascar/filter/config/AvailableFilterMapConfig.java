package com.sascar.filter.config;

import com.sascar.filter.filters.AbstractFilter;
import com.sascar.filter.filters.impl.ClientIdFilter;
import com.sascar.filter.filters.impl.VehicleIdFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AvailableFilterMapConfig {

    @Bean
    public Map<String, Class<? extends AbstractFilter>> availableFilters() {
        Map<String, Class<? extends AbstractFilter>> map = new HashMap<>();
        map.put("clientId", ClientIdFilter.class);
        map.put("vehicleId", VehicleIdFilter.class);
        return map;
    }
}