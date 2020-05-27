package com.sascar.filter.filters.impl;

import com.sascar.filter.dto.Position;
import com.sascar.filter.dto.ResourceConfig;
import com.sascar.filter.filters.AbstractFilter;
import org.springframework.util.Assert;

public class ClientIdFilter extends AbstractFilter {
    private int clientId;

    @Override
    public boolean validate(Position position) {
        Assert.notNull(position, "position cannot be empty.");
        return this.clientId == position.getClientId();
    }

    @Override
    public boolean validateConfiguration(ResourceConfig resourceConfig) {
        Assert.notNull(resourceConfig, "resourceConfig cannot be empty.");
        return resourceConfig.getValidate().getClientId() != 0;
    }

    @Override
    public void setConditionValue(Object value) {
        Assert.notNull(value, "value cannot be null");
        this.clientId = (int) value;
    }
}