package com.sascar.filter.config;

import com.sascar.filter.dto.ConfigurationFilter;
import com.sascar.filter.dto.ResourceConfig;
import com.sascar.filter.factory.FilterConfigurationFactory;
import com.sascar.filter.populator.ConfigurationFilterPopulator;
import com.sascar.filter.services.FilterResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AvailableFilterConfigs {
    private final FilterResourceService filterResourceService;
    private final FilterConfigurationFactory filterConfigurationFactory;
    private final ConfigurationFilterPopulator configurationFilterPopulator;

    @Bean
    public List<ConfigurationFilter> filterConfigs() {
        final List<ConfigurationFilter> filters = new ArrayList<>();
        try {
            final Resource[] configurationFiles = this.filterResourceService.getConfigurationFiles();
            for(final Resource resource : configurationFiles) {
                final ResourceConfig config = this.filterConfigurationFactory.create(resource);
                final ConfigurationFilter filter = this.configurationFilterPopulator.populate(config);
                filters.add(filter);
            }
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
        return filters;
    }
}