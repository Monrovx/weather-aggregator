package com.company.weather_aggregator.service.impl;

import com.company.weather_aggregator.client.weather_api_client.WeatherApiClientService;
import com.company.weather_aggregator.client.weather_api_client.dto.WeatherApiResponse;
import com.company.weather_aggregator.dto.DayForecast;
import com.company.weather_aggregator.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

@Service
public class WeatherApiService implements WeatherService {
    private static final String SERVICE_NAME = "WeatherApi service";
    private static final int OFFSET_FOR_ONE_DAY = 1;
    private static final int OFFSET_FOR_WEEK = 8;
    @Autowired
    WeatherApiClientService weatherApiClientService;

    @Override
    @Async
    public CompletableFuture<Map.Entry<String, List<DayForecast>>> getWeatherForTomorrow(String city) {
        Optional<WeatherApiResponse> response = weatherApiClientService.getWeather(city, OFFSET_FOR_ONE_DAY);
        return completedFuture(getProcessedWeatherApiResponse(response));
    }

    @Override
    @Async
    public CompletableFuture<Map.Entry<String, List<DayForecast>>> getWeatherForWeek(String city) {
        Optional<WeatherApiResponse> response = weatherApiClientService.getWeather(city, OFFSET_FOR_WEEK);
        return completedFuture(getProcessedWeatherApiResponse(response));
    }

    private Map.Entry<String, List<DayForecast>> getProcessedWeatherApiResponse(Optional<WeatherApiResponse> response) {
        if (response.isEmpty()) return Map.entry(SERVICE_NAME, new ArrayList<>());

        String location = response.get().location().toString();
        List<DayForecast> forecasts = response.get().forecast().forecastday().stream()
                .skip(1)
                .map(forecast ->
                        new DayForecast(location, forecast.day().maxtemp_c(),
                                forecast.day().condition().text(),
                                forecast.day().getMaxWind_ms(),
                                forecast.date()
                        )
                ).toList();
        return Map.entry(SERVICE_NAME, forecasts);
    }
}
