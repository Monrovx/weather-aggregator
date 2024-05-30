package com.company.weather_aggregator.client.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "visual-crossing")
public class VisualCrossingProperties {
    private String url;
    private String apiKey;
    private String aggregateHours;
    private String unitGroup;
    private String contentType;
}
