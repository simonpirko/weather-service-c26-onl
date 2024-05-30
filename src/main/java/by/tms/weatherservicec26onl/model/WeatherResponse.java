package by.tms.weatherservicec26onl.model;

import lombok.Data;

@Data
public class WeatherResponse {

        private String time;
        private double temp_c;
        private String condition;
        private double wind_kph;
        private int wind_degree;
        private double pressure_mb;
        private double precip_mm;
        private int humidity;
        private int cloud;
        private double windchill_c;
        private double vis_km;
        private double gust_kph;
        private double uv;

}