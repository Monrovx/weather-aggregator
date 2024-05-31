package com.company.weather_aggregator.service.impl;

import com.company.weather_aggregator.dto.DayForecast;
import com.company.weather_aggregator.dto.WeatherResponseDto;
import com.company.weather_aggregator.service.ServiceAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.allOf;

@Service
@ConditionalOnProperty(value = "active-client.type", havingValue = "all")
public class ServiceAggregatorAllClients implements ServiceAggregator {
    @Autowired
    WeatherApiService weatherApiService;
    @Autowired
    VisualCrossingService visualCrossingService;

    @Override
    public WeatherResponseDto getAggregatedInfoForTomorrow(String city) {
        CompletableFuture<Map.Entry<String, List<DayForecast>>> weatherApiResponseFuture =
                weatherApiService.getWeatherForTomorrow(city);
        CompletableFuture<Map.Entry<String, List<DayForecast>>> visualCrossingResponseFuture =
                visualCrossingService.getWeatherForTomorrow(city);

        return getWeatherResponseDto(weatherApiResponseFuture, visualCrossingResponseFuture);
    }

    @Override
    public WeatherResponseDto getAggregatedInfoForWeek(String city) {
        CompletableFuture<Map.Entry<String, List<DayForecast>>> weatherApiResponseFuture =
                weatherApiService.getWeatherForWeek(city);
        CompletableFuture<Map.Entry<String, List<DayForecast>>> visualCrossingResponseFuture =
                visualCrossingService.getWeatherForWeek(city);

        return getWeatherResponseDto(weatherApiResponseFuture, visualCrossingResponseFuture);
    }

    private WeatherResponseDto getWeatherResponseDto(
            CompletableFuture<Map.Entry<String, List<DayForecast>>> weatherApiResponseFuture,
            CompletableFuture<Map.Entry<String, List<DayForecast>>> visualCrossingResponseFuture
    ) {
        allOf(weatherApiResponseFuture, visualCrossingResponseFuture).join();

        Map.Entry<String, List<DayForecast>> weatherApiResponse = weatherApiResponseFuture.join();
        Map.Entry<String, List<DayForecast>> visualCrossingResponse = visualCrossingResponseFuture.join();
        Map<String, List<DayForecast>> aggregatedResponse = Map.ofEntries(weatherApiResponse, visualCrossingResponse);
        return new WeatherResponseDto(aggregatedResponse);
    }
}
