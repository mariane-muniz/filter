package com.sascar.filter.services.impl;

import com.sascar.filter.services.FilterResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FilterResourceServiceImpl implements FilterResourceService {

    @Value("${filter.configuration.file.directory}")
    private String pattern;
    private final ResourceLoader resourceLoader;

    @Override
    public Resource[] getConfigurationFiles() throws IOException {
        return ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(pattern);
    }
}