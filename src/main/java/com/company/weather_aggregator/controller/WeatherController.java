package com.company.weather_aggregator.controller;

import com.company.weather_aggregator.dto.WeatherResponse;
import com.company.weather_aggregator.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping(path = "/weather/today")
    public ResponseEntity<WeatherResponse> getWeatherForToday(
            @RequestParam(name = "city", defaultValue = "Krasnodar") String city) {
        return ResponseEntity.ok(weatherService.getWeatherForToday(city));
    }

    @GetMapping(path = "/weather/week")
    public ResponseEntity<WeatherResponse> getWeatherForWeek(
            @RequestParam(name = "city", defaultValue = "Krasnodar") String city) {
        return ResponseEntity.ok(weatherService.getWeatherForWeek(city));
    }
}
