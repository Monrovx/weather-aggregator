package com.company.weather_aggregator.client.visual_crossing_client.dto;

import java.util.Map;

public record VisualCrossingResponse(
        Map<String, Forecast> locations
) {
}
