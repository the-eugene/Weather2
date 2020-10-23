package com.example.weather2;

import android.util.Log;

import com.google.gson.Gson;

public class City implements Runnable{

    boolean fullForecast=false;
    String queryType;
    String location;
    WeatherConditions current=null;
    FiveDayForecast forecast=null;
    Float maxTemp;
    Float maxWind;

    Gson g = new Gson();

    City(){
        queryType="q";
        location="Rexburg";
    }

    City(String location){
        queryType="q";
        if (location.isEmpty()){
            location="Rexburg";
        }
        this.location=location;
    }

    City(String queryType, String location){
        this.queryType=queryType;
        this.location=location;
    }

    public String toString(){
        return forecast.city.name+", "+forecast.city.country;
    }

    public String getCurrentWeather(){
        if(current==null){
            current=loadCurrentWeather();
        }
        return current.toString();
    }

    public String getForecast(){
        if(forecast==null){
            forecast=loadForecast();
        }
        return forecast.toString();
    }

    private WeatherConditions loadCurrentWeather() {
        String json= WeatherAPI.getSimpleJSON(queryType,location);
        return g.fromJson(json,WeatherConditions.class);
    }

    private FiveDayForecast loadForecast() {
        String json= WeatherAPI.getForecastJSON(queryType,location);
        FiveDayForecast f=g.fromJson(json, FiveDayForecast.class);
        maxTemp=f.findMaxTemp();
        maxWind=f.findMaxWind();
        return f;
    }

    @Override
    public void run() {
        Log.d("com.example.weather2", location);
        try {
            if (!fullForecast) {
                WeatherConditions w = loadCurrentWeather();
                Float temp = w.measurements.get("temp");
                AppUI.toast("Temperature is: " + temp);
            } else {
                FiveDayForecast f = loadForecast();
                AppUI.list(f);
            }
        }
        catch (Exception e){
            Log.d("com.example.weather2","Could not get Weather");
            AppUI.toast("Could not retrieve city weather");
        }
    }
}
