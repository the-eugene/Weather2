
package com.example.weather2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FiveDayForecast {
    @SerializedName("cnt")
    @Expose
    public Integer countResponses=0;
    @SerializedName("list")
    @Expose
    public List<WeatherConditions> catalog = null;
    @SerializedName("city")
    @Expose
    public About city;

    public String toString(){
        StringBuilder text = new StringBuilder(String.format("5 Day Forecast for: %s, %s\n", city.name, city.country));
        for (WeatherConditions item:catalog) {
            text.append(item.toString("     "));
        }
        return text.toString();
    }

    public float findMaxTemp() {
        float max=0;
        for (WeatherConditions w:catalog) {
            max=Math.max(w.measurements.get("temp"),max);
        }
        return max;
    }

    public float findMaxWind() {
        float max=0;
        for (WeatherConditions w:catalog) {
            max=Math.max(w.wind.speed,max);
        }
        return max;
    }
}
