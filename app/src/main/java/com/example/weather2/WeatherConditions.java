
package com.example.weather2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class WeatherConditions {

    @SerializedName("dt")
    @Expose
    public Integer dt; //Unix UTC Time
    @SerializedName("main")
    @Expose
    public Map<String,Float> measurements;
    @SerializedName("weather")
    @Expose
    public List<Skies> weather = null;
    @SerializedName("wind")
    @Expose
    public Wind wind;

    public String toString(){
        return toString("");
    }

    public String toString(String indent){
        Date date=new Date((long)dt*1000);
        String dt_text=String.format("%s\n", date.toString());
        String temp_text=String.format(indent+"Temperature: %.0fF\n",measurements.get("temp"));
        String weather_text=String.format(indent+"Conditions: %s - %s\n", weather.get(0).mainCondition,weather.get(0).description);
        String wind_txt=String.format(indent+"Wind Speed: %.1f\n",wind.speed);
        return dt_text+temp_text+weather_text+wind_txt+"\n";
    }
}