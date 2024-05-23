package by.tms.weatherservicec26onl.service;

import by.tms.weatherservicec26onl.serialization.WeatherResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class WeatherService {
    private final RestTemplate restTemplate;

    public WeatherResponse getWeather(){
        String url = "https://weatherapi.com";
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to get weather");
        }

        return response.getBody();
    }
}
