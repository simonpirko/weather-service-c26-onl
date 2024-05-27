package by.tms.weatherservicec26onl.controller;


import by.tms.weatherservicec26onl.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/now/{location}")
    public String now(@PathVariable String location) {
        String apiUrl = weatherService.createUrl("/current.json", "q", location);
        return weatherService.getWeatherData(apiUrl);
    }

    @GetMapping("/forecast/{location}/{days}")
    public String forecast(@PathVariable String location, @PathVariable String days) {
        String apiUrl = weatherService.createUrl("/forecast.json", "q", location, "days", days);
        return weatherService.getWeatherData(apiUrl);
    }

}
