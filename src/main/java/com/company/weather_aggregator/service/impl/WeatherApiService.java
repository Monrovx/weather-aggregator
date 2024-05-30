package com.company.weather_aggregator.service.impl;

import com.company.weather_aggregator.client.weather_api_client.WeatherApiClientService;
import com.company.weather_aggregator.client.weather_api_client.dto.WeatherApiResponse;
import com.company.weather_aggregator.dto.DayForecast;
import com.company.weather_aggregator.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WeatherApiService implements WeatherService {
    private static final String serviceName = "WeatherApi service";
    @Autowired
    WeatherApiClientService weatherApiClientService;


    @Override
    public Map.Entry<String, List<DayForecast>> getWeatherForToday(String city) {
        WeatherApiResponse response = weatherApiClientService.getWeather(city, 1);
        return getProcessedWeatherApiResponse(response);
    }

    @Override
    public Map.Entry<String, List<DayForecast>> getWeatherForWeek(String city) {
        WeatherApiResponse response = weatherApiClientService.getWeather(city, 7);
        return getProcessedWeatherApiResponse(response);
    }

    private Map.Entry<String, List<DayForecast>> getProcessedWeatherApiResponse(WeatherApiResponse response) {
        String location = response.location().toString();
        List<DayForecast> forecasts = response.forecast().forecastday().stream()
                .map(forecast ->
                        new DayForecast(location, forecast.day().maxtemp_c(),
                                forecast.day().condition().text(),
                                forecast.day().getMaxWind_ms(),
                                forecast.date()
                        )
                ).toList();
        return Map.entry(serviceName, forecasts);
    }
}
