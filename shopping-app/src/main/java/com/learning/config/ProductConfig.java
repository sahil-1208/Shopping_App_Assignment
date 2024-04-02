package com.learning.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "product")
@Data
public class ProductConfig {
    private Double price;
    private Integer available;
}
