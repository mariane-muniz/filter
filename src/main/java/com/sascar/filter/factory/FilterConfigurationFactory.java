package com.sascar.filter.factory;

import com.sascar.filter.dto.ResourceConfig;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

@Service
public class FilterConfigurationFactory {

    public ResourceConfig create(final Resource resource) {
        Assert.notNull(resource, "resource cannot be null.");
        final String fileName = resource.getFilename();
        Yaml yaml = new Yaml(new Constructor(ResourceConfig.class));
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("filters/" + fileName);
        return yaml.load(inputStream);
    }
}