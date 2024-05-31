package com.company.weather_aggregator.clients.visual_crossing_client.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record WeatherInfo(
        OffsetDateTime datetimeStr,
        BigDecimal temp,
        BigDecimal maxt,
        BigDecimal wspd,
        String conditions
) {
}
