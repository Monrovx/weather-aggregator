package com.company.weather_aggregator.client.visual_crossing_client;

import com.company.weather_aggregator.client.properties.VisualCrossingProperties;
import com.company.weather_aggregator.client.visual_crossing_client.dto.VisualCrossingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.View;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
@Slf4j
public class VisualCrossingWeatherClientService {
    @Autowired
    private WebClient visualCrossingWebClient;
    @Autowired
    private VisualCrossingProperties properties;
    @Autowired
    private View error;

    public Optional<VisualCrossingResponse> getWeather(String city, Integer days) throws WebClientResponseException {
        log.info("In: visualCrossingWebClient: City: {}, Days: {}", city, days);

        var uriTemplate =  UriComponentsBuilder.fromUriString(properties.getUrl())
                .queryParam("locations", "{city}")
                .queryParam("aggregateHours", "{aggregateHours}")
                .queryParam("unitGroup", "{unitGroup}")
                .queryParam("contentType", "{contentType}")
                .queryParam("key", "{apiKey}")
                .queryParam("forecastDays", "{days}")
                .encode().toUriString();

        Optional<VisualCrossingResponse> response = visualCrossingWebClient.get()
                .uri(uriTemplate, city, properties.getAggregateHours(), properties.getUnitGroup(),
                        properties.getContentType(), properties.getApiKey(), days)
                .retrieve()
                .onStatus(HttpStatusCode::isError, ClientResponse::createError)
                .bodyToMono(VisualCrossingResponse.class)
                .doOnError( error -> log.warn("VisualCrossingWebClient - " + error.getMessage(), error))
                .onErrorComplete()
                .blockOptional();

        log.info("Out: visualCrossingWebClient: Response: {}", response);
        return response;
    }
}
