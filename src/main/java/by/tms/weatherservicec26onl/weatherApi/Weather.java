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


    @GetMapping("/now/{location}")
    public String now(@PathVariable String location) {
        String apiUrl = weatherConstants.apiUrl + "/current.json?q=" + location;

        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "&key=" + weatherConstants.apiKey;
        String response = restTemplate.getForObject(url, String.class);

        return response;
    }

    @GetMapping("/forecast/{location}/{days}")
    public String forecast(@PathVariable String location, @PathVariable int days) {
        String apiUrl = weatherConstants.apiUrl + "/forecast.json?q=" + location + "&days=" + days;

        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "&key=" + weatherConstants.apiKey;
        String response = restTemplate.getForObject(url, String.class);

        return response;
    }

}
