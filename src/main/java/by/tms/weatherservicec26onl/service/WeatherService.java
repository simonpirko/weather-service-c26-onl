package by.tms.weatherservicec26onl.service;

import by.tms.weatherservicec26onl.constants.weatherConstants;
import by.tms.weatherservicec26onl.model.WeatherResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@AllArgsConstructor
public class WeatherService {
  
    private final RestTemplate restTemplate = new RestTemplate();

    private String createUrl(String path, String... queryParams) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(weatherConstants.apiUrl);
        uriBuilder.path(path);
        for (String paramName : queryParams) {
            uriBuilder.queryParam(paramName, weatherConstants.apiKey);
        }
        return uriBuilder.build().toUriString();
    }

    public ResponseEntity<WeatherResponse> getWeatherData(String location) {
        String apiUrl = createUrl("/current.json", "q", location);
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(apiUrl, WeatherResponse.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to get weather");
        }
        return response;
    }

    public ResponseEntity<WeatherResponse> getWeatherData(String location, String days) {
        String apiUrl = createUrl("/forecast.json", "q", location, "days", days);
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(apiUrl, WeatherResponse.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to get weather");
        }
        return response;
    }
}
