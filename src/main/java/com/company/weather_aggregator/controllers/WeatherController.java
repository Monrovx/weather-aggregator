package com.company.weather_aggregator.controllers;

import com.company.weather_aggregator.dto.WeatherResponseDto;
import com.company.weather_aggregator.services.ServiceAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    @Autowired
    private ServiceAggregator serviceAggregator;

    @GetMapping(path = "/weather/today")
    public ResponseEntity<WeatherResponseDto> getWeatherForTomorrow(
            @RequestParam(name = "city", defaultValue = "Krasnodar") String city) {
        return ResponseEntity.ok(serviceAggregator.getAggregatedInfoForToday(city));
    }

    @GetMapping(path = "/weather/week")
    public ResponseEntity<WeatherResponseDto> getWeatherForWeek(
            @RequestParam(name = "city", defaultValue = "Krasnodar") String city) {
        return ResponseEntity.ok(serviceAggregator.getAggregatedInfoForWeek(city));
    }
}
