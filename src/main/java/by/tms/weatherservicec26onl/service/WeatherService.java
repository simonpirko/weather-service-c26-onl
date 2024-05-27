package by.tms.weatherservicec26onl.service;

import by.tms.weatherservicec26onl.constants.weatherConstants;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@AllArgsConstructor
public class WeatherService {
  
    private RestTemplate restTemplate = new RestTemplate();

    public String createUrl(String path, String... queryParams) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(weatherConstants.apiUrl);
        uriBuilder.path(path);
        for (String paramName : queryParams) {
            uriBuilder.queryParam(paramName, weatherConstants.apiKey);
        }
        return uriBuilder.build().toUriString();
    }

    public WeatherResponse getWeatherData(String url) {
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);
      
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to get weather");
        }
      
        return response.getBody();
    }
}
