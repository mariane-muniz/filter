package com.sascar.filter.filters;

import com.sascar.filter.dto.Position;
import com.sascar.filter.dto.ResourceConfig;

public abstract class AbstractFilter {
    public abstract boolean validate(Position position);
    public abstract boolean validateConfiguration(ResourceConfig resourceConfig);
    public abstract void setConditionValue(final Object value);
}