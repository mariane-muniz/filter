package com.sascar.filter.filters.impl;

import com.sascar.filter.dto.Position;
import com.sascar.filter.dto.ResourceConfig;
import com.sascar.filter.filters.AbstractFilter;
import org.springframework.util.Assert;

public class VehicleIdFilter extends AbstractFilter {
    private int vehicleId;

    @Override
    public boolean validate(Position position) {
        Assert.notNull(position, "position cannot be null");
        return this.vehicleId == position.getVehicleId();
    }

    @Override
    public boolean validateConfiguration(ResourceConfig resourceConfig) {
        Assert.notNull(resourceConfig, "resourceConfig cannot be null");
        return resourceConfig.getValidate().getVehicleId() != 0;
    }

    @Override
    public void setConditionValue(Object value) {
        Assert.notNull(value, "value cannot be null");
        this.vehicleId = (int) value;
    }
}