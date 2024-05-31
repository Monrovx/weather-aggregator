package com.company.weather_aggregator.clients.visual_crossing_client.dto;

import java.util.Map;


public record VisualCrossingResponse(
    Map<String, Forecast> locations
) {
}
