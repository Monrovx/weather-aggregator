package com.company.weather_aggregator.client.weather_api_client;

import com.company.weather_aggregator.client.properties.WeatherApiProperties;
import com.company.weather_aggregator.client.weather_api_client.dto.WeatherApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@Slf4j
public class WeatherApiClientService {
    @Autowired
    private WebClient weatherApiWebClient;
    @Autowired
    private WeatherApiProperties properties;

    public Optional<WeatherApiResponse> getWeather(String city, Integer days) {
        log.info("In: weatherApiWebClient: City: {}, Days: {}", city, days);

        var uriTemplate =  UriComponentsBuilder.fromUriString(properties.getUrl())
                .queryParam("q", "{city}")
                .queryParam("lang", "{language}")
                .queryParam("key", "{apiKey}")
                .queryParam("days", "{days}")
                .encode().toUriString();

        Optional<WeatherApiResponse> response = weatherApiWebClient.get()
                .uri(uriTemplate, city, properties.getLanguage(), properties.getApiKey(), days)
                .retrieve()
                .onStatus(HttpStatusCode::isError, ClientResponse::createError)
                .bodyToMono(WeatherApiResponse.class)
                .doOnError( error ->
                        log.warn("WeatherApiWebClient - " + error.getMessage(), error.getLocalizedMessage()))
                .onErrorComplete()
                .blockOptional();

        log.info("Out: weatherApiWebClient: Response: {}", response);
        return response;
    }
}
