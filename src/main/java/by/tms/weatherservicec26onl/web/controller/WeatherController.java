package by.tms.weatherservicec26onl.web.controller;

import by.tms.weatherservicec26onl.model.WeatherResponse;
import by.tms.weatherservicec26onl.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@AllArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public WeatherResponse getWeather(@RequestParam String apiKey) {
        WeatherResponse weather = weatherService.getWeather();
        return weather;
    }
}
