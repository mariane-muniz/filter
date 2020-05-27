package com.sascar.filter.services;

import org.springframework.core.io.Resource;

import java.io.IOException;

public interface FilterResourceService {
    Resource[] getConfigurationFiles() throws IOException;
}
