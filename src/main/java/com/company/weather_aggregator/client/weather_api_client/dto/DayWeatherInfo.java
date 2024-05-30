package com.company.weather_aggregator.client.weather_api_client.dto;

import java.math.BigDecimal;
import java.math.MathContext;

public record DayWeatherInfo(
        BigDecimal maxtemp_c,
        BigDecimal maxwind_kph,
        Condition condition
) {
    public BigDecimal getMaxWind_ms() {
        return maxwind_kph.divide(BigDecimal.valueOf(3.6), MathContext.DECIMAL32);
    }
}
