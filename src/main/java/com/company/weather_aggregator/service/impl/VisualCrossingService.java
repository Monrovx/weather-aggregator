package com.company.weather_aggregator.service.impl;

import com.company.weather_aggregator.client.visual_crossing_client.VisualCrossingWeatherClientService;
import com.company.weather_aggregator.client.visual_crossing_client.dto.Forecast;
import com.company.weather_aggregator.client.visual_crossing_client.dto.VisualCrossingResponse;
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
public class VisualCrossingService implements WeatherService {
    private static final String VISUAL_CROSSING_SERVICE_NAME = "VisualCrossing service";
    private static final int OFFSET_FOR_ONE_DAY = 2;
    private static final int OFFSET_FOR_WEEK = 8;
    @Autowired
    VisualCrossingWeatherClientService visualCrossingClient;

    @Override
    @Async
    public CompletableFuture<Map.Entry<String, List<DayForecast>>> getWeatherForTomorrow(String city) {
        Optional<VisualCrossingResponse> response = visualCrossingClient.getWeather(city, OFFSET_FOR_ONE_DAY);
        return completedFuture(visualCrossingProcessing(city, response));
    }

    @Override
    @Async
    public CompletableFuture<Map.Entry<String, List<DayForecast>>> getWeatherForWeek(String city) {
        Optional<VisualCrossingResponse> response = visualCrossingClient.getWeather(city, OFFSET_FOR_WEEK);
        return completedFuture(visualCrossingProcessing(city, response));
    }

    private Map.Entry<String, List<DayForecast>> visualCrossingProcessing(
            String city, Optional<VisualCrossingResponse> response
    ) {
        if (response.isEmpty()) return Map.entry(VISUAL_CROSSING_SERVICE_NAME, new ArrayList<>());

        Forecast forecast = response.get().locations().get(city);
        List<DayForecast> dayForecastList = forecast.values().stream()
                .skip(2)
                .map(info -> new DayForecast(forecast.address(), info.temp(), info.conditions(),
                        info.wspd(), info.datetimeStr().toLocalDate())
                ).toList();
        return Map.entry(VISUAL_CROSSING_SERVICE_NAME, dayForecastList);
    }
}
