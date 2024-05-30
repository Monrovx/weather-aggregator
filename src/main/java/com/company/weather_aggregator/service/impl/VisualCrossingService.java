package com.company.weather_aggregator.service.impl;

import com.company.weather_aggregator.client.visual_crossing_client.VisualCrossingWeatherClientService;
import com.company.weather_aggregator.client.visual_crossing_client.dto.Forecast;
import com.company.weather_aggregator.client.visual_crossing_client.dto.VisualCrossingResponse;
import com.company.weather_aggregator.dto.DayForecast;
import com.company.weather_aggregator.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VisualCrossingService implements WeatherService {
    private static final String visualCrossingServiceName = "VisualCrossing service";
    @Autowired
    VisualCrossingWeatherClientService visualCrossingClient;
    @Override
    public Map.Entry<String, List<DayForecast>> getWeatherForToday(String city) {
        VisualCrossingResponse response = visualCrossingClient.getWeather(city, 1);
        Map.Entry<String, List<DayForecast>> processedResponse = visualCrossingProcessing(city, response);
        return Map.entry(processedResponse.getKey(), processedResponse.getValue());
    }

    @Override
    public Map.Entry<String, List<DayForecast>> getWeatherForWeek(String city) {
        VisualCrossingResponse response = visualCrossingClient.getWeather(city, 7);
        Map.Entry<String, List<DayForecast>> processedResponse = visualCrossingProcessing(city, response);
        return Map.entry(processedResponse.getKey(), processedResponse.getValue());
    }

    private Map.Entry<String, List<DayForecast>> visualCrossingProcessing(String city, VisualCrossingResponse response) {
        Forecast forecast = response.locations().get(city);
        List<DayForecast> dayForecastList = forecast.values().stream()
                .skip(1)
                .map(info -> new DayForecast(forecast.address(), info.temp(), info.conditions(),
                        info.wspd(), info.datetimeStr().toLocalDate())
                ).toList();
        return Map.entry(visualCrossingServiceName, dayForecastList);
    }
}
