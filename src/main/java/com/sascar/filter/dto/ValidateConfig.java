package com.sascar.filter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateConfig {
    private Integer clientId;
    private Integer vehicleId;
    private Integer driverId;

    public List<String> getNotNullFields() throws IllegalAccessException {
        final List<String> nonNullFields = new ArrayList<>();
        for(final Field field : this.getClass().getDeclaredFields()) {
            if (Objects.nonNull(field.get(this))) nonNullFields.add(field.getName());
        }
        return nonNullFields;
    }
}