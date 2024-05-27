package by.tms.weatherservicec26onl.weatherApi;


import by.tms.weatherservicec26onl.constants.weatherConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/weather")
public class Weather {

    private String getWeatherData(String url) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = url + "&key=" + weatherConstants.apiKey;
        return restTemplate.getForObject(apiUrl, String.class);
    }

    @GetMapping("/now/{location}")
    public String now(@PathVariable String location) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(weatherConstants.apiUrl);
        uriBuilder.path("/current.json");
        uriBuilder.queryParam("q", location);
        uriBuilder.queryParam("key", weatherConstants.apiKey);
        String apiUrl = uriBuilder.build().toUriString();

        return getWeatherData(apiUrl);
    }

    @GetMapping("/forecast/{location}/{days}")
    public String forecast(@PathVariable String location, @PathVariable int days) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(weatherConstants.apiUrl);
        uriBuilder.path("/forecast.json");
        uriBuilder.queryParam("q", location);
        uriBuilder.queryParam("days", days);
        uriBuilder.queryParam("key", weatherConstants.apiKey);
        String apiUrl = uriBuilder.build().toUriString();

        return getWeatherData(apiUrl);
    }

}
