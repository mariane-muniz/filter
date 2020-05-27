package com.sascar.filter.dto;

import com.sascar.filter.filters.AbstractFilter;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ConfigurationFilter {
    private List<AbstractFilter> filters;
    private List<String> fields;
    private String filteredTopicName;

    {
        this.filters = new ArrayList<>();
        this.fields = new ArrayList<>();
    }
}