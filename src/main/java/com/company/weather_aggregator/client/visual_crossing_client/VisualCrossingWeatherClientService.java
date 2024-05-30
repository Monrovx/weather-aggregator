package com.company.weather_aggregator.client.visual_crossing_client;

import com.company.weather_aggregator.client.properties.VisualCrossingProperties;
import com.company.weather_aggregator.client.visual_crossing_client.dto.VisualCrossingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class VisualCrossingWeatherClientService {
    @Autowired
    private WebClient visualCrossingWebClient;
    @Autowired
    private VisualCrossingProperties properties;

    public VisualCrossingResponse getWeather(String city, Integer days) {
        var uriTemplate =  UriComponentsBuilder.fromUriString(properties.getUrl())
                .queryParam("locations", "{city}")
                .queryParam("aggregateHours", "{aggregateHours}")
                .queryParam("unitGroup", "{unitGroup}")
                .queryParam("contentType", "{contentType}")
                .queryParam("key", "{apiKey}")
                .queryParam("forecastDays", "{days}")
                .encode().toUriString();

        VisualCrossingResponse response = visualCrossingWebClient.get()
                .uri(uriTemplate, city, properties.getAggregateHours(), properties.getUnitGroup(),
                        properties.getContentType(), properties.getApiKey(), days)
                .retrieve()
                .bodyToMono(VisualCrossingResponse.class)
                .block();
        System.out.println(response);
        return response;

    }
}
