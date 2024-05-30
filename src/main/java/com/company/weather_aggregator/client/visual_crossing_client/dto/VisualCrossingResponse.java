package com.company.weather_aggregator.client.visual_crossing_client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;


public record VisualCrossingResponse(
    Map<String, Forecast> locations
) {
}
