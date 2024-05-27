package by.tms.weatherservicec26onl.weatherApi;


import by.tms.weatherservicec26onl.constants.weatherConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
        String apiUrl = weatherConstants.apiUrl + "/current.json?q=" + location;

        return getWeatherData(apiUrl);
    }

    @GetMapping("/forecast/{location}/{days}")
    public String forecast(@PathVariable String location, @PathVariable int days) {
        String apiUrl = weatherConstants.apiUrl + "/forecast.json?q=" + location + "&days=" + days;

        return getWeatherData(apiUrl);
    }

}
