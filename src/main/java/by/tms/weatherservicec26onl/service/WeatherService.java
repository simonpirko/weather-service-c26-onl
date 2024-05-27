package by.tms.weatherservicec26onl.service;

import by.tms.weatherservicec26onl.constants.weatherConstants;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    public String createUrl(String path, String... queryParams) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(weatherConstants.apiUrl);
        uriBuilder.path(path);
        for (String paramName : queryParams) {
            uriBuilder.queryParam(paramName, weatherConstants.apiKey);
        }
        return uriBuilder.build().toUriString();
    }

    public String getWeatherData(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

}
