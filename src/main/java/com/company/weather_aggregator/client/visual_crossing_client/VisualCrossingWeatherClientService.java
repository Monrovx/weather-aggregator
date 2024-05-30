package com.company.weather_aggregator.client.visual_crossing_client;

import com.company.weather_aggregator.client.properties.VisualCrossingProperties;
import com.company.weather_aggregator.client.visual_crossing_client.dto.VisualCrossingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class VisualCrossingWeatherClientService {
    @Autowired
    private WebClient visualCrossingWebClient;
    @Autowired
    private VisualCrossingProperties properties;

    public VisualCrossingResponse getWeather(String city, Integer days) {
        log.info("In: visualCrossingWebClient: City: {}, Days: {}", city, days);

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
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        Mono.error(new RuntimeException("ErrorResponse : " + clientResponse.bodyToMono(String.class))))
                .bodyToMono(VisualCrossingResponse.class)
//                .doOnSuccess(body -> log.debug("Out: weatherApiWebClient: Response: {}", body))
//                .doOnError(exception -> log.warn("Failed to request, cause {}", exception.getMessage()))
//                .single()
                .block();
        return response;

    }
}
