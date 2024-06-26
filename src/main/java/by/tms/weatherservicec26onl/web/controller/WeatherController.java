package by.tms.weatherservicec26onl.web.controller;


import by.tms.weatherservicec26onl.model.WeatherResponse;
import by.tms.weatherservicec26onl.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<WeatherResponse> now(@PathVariable String location) {
        return weatherService.getWeatherData(location);
    }

    @GetMapping("/forecast/{location}/{days}")
    public ResponseEntity<WeatherResponse> forecast(@PathVariable String location, @PathVariable String days) {
        return weatherService.getWeatherData(location, days);
    }

}
