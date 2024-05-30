package com.company.weather_aggregator.client.weather_api_client;

import com.company.weather_aggregator.client.properties.WeatherApiProperties;
import com.company.weather_aggregator.client.weather_api_client.dto.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherApiClientService {
    @Autowired
    private WebClient weatherApiWebClient;
    @Autowired
    private WeatherApiProperties properties;

    public WeatherApiResponse getWeather(String city, Integer days) {
        var uriTemplate =  UriComponentsBuilder.fromUriString(properties.getUrl())
                .queryParam("q", "{city}")
                .queryParam("lang", "{language}")
                .queryParam("key", "{apiKey}")
                .queryParam("days", "{days}")
                .encode().toUriString();

        WeatherApiResponse response = weatherApiWebClient.get()
                .uri(uriTemplate, city, properties.getLanguage(), properties.getApiKey(), days)
                .retrieve()
                .bodyToMono(WeatherApiResponse.class)
                .block();
        System.out.println(response);
        return response;

    }
}
