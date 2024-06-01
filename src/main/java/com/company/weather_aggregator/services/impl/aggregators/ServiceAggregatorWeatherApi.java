package com.company.weather_aggregator.services.impl.aggregators;

import com.company.weather_aggregator.dto.DayForecast;
import com.company.weather_aggregator.dto.WeatherResponseDto;
import com.company.weather_aggregator.services.ServiceAggregator;
import com.company.weather_aggregator.services.impl.api_services.VisualCrossingService;
import com.company.weather_aggregator.services.impl.api_services.WeatherApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@ConditionalOnProperty(value = "active-client.type", havingValue = "weather-api")
public class ServiceAggregatorWeatherApi implements ServiceAggregator {
    @Autowired
    WeatherApiService weatherApiService;
    @Autowired
    VisualCrossingService visualCrossingService;

    @Override
    public WeatherResponseDto getAggregatedInfoForToday(String city) {
        CompletableFuture<Map.Entry<String, List<DayForecast>>> weatherApiResponseFuture =
                weatherApiService.getWeatherForToday(city);

        return getWeatherResponseDto(weatherApiResponseFuture);
    }

    @Override
    public WeatherResponseDto getAggregatedInfoForWeek(String city) {
        CompletableFuture<Map.Entry<String, List<DayForecast>>> weatherApiResponseFuture =
                weatherApiService.getWeatherForWeek(city);

        return getWeatherResponseDto(weatherApiResponseFuture);
    }

    private WeatherResponseDto getWeatherResponseDto(
            CompletableFuture<Map.Entry<String, List<DayForecast>>> weatherApiResponseFuture
    ) {
        Map.Entry<String, List<DayForecast>> weatherApiResponse = weatherApiResponseFuture.join();
        Map<String, List<DayForecast>> aggregatedResponse = Map.ofEntries(weatherApiResponse);
        return new WeatherResponseDto(aggregatedResponse);
    }
}