package com.sascar.filter.populator;

import com.sascar.filter.dto.ConfigurationFilter;
import com.sascar.filter.dto.ResourceConfig;
import com.sascar.filter.filters.AbstractFilter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConfigurationFilterPopulator {
    private final Map<String, Class<? extends AbstractFilter>> availableFilters;

    public ConfigurationFilter populate(final ResourceConfig resourceConfig) {
        final ConfigurationFilter dto = new ConfigurationFilter();
        final List<AbstractFilter> filters = this.getFilters(resourceConfig);
        dto.setFilters(filters);
        return dto;
    }

    private List<AbstractFilter> getFilters(final ResourceConfig resourceConfig) {
        List<AbstractFilter> filters = new ArrayList<>();
        try {
            List<String> notNullFields = resourceConfig.getValidate().getNotNullFields();
            notNullFields.iterator().forEachRemaining(fieldName -> {
                if (this.availableFilters.containsKey(fieldName)) {
                    final AbstractFilter filter = this.getClasInstance(fieldName);
                    final Object value = this.getResourceFieldValue(resourceConfig, fieldName);
                    filter.setConditionValue(value);
                    filters.add(filter);
                }
                else log.error("key not found: " + fieldName);
            });
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
        }
        return filters;
    }

    @SneakyThrows
    private Object getResourceFieldValue(ResourceConfig resourceConfig, final String fieldName) {
        return FieldUtils.readField(resourceConfig.getValidate(), fieldName, true);
    }

    @SneakyThrows
    private AbstractFilter getClasInstance(final String fieldName) {
        final Class<? extends AbstractFilter> clazz = this.availableFilters.get(fieldName);
        return clazz.getConstructor().newInstance();
    }
}