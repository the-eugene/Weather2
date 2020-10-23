package com.example.weather2;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WeatherAPI {
    static char paramDelimiter ='&';
    static String baseURL = "https://api.openweathermap.org/data/2.5/weather";
    static String baseForecastURL = "https://api.openweathermap.org/data/2.5/forecast";
    static String charset = java.nio.charset.StandardCharsets.UTF_8.name();

    public static String getSimpleJSON(String queryType,String location){
        Map<String, String> parameters=new HashMap<>();
        parameters.put(queryType,location);
        parameters.put("units","imperial");
        parameters.put("apiKey", Secret.WeatherAPI);

        return getResponse(getURL(baseURL,parameters));
    }

    public static String getForecastJSON(String type,String location){
        Map<String, String> parameters=new HashMap<>();
        parameters.put(type,location);
        parameters.put("units","imperial");
        parameters.put("apiKey", Secret.WeatherAPI);

        return getResponse(getURL(baseForecastURL,parameters));
    }

    private static String getResponse(String url){
        InputStream response=null;
        URLConnection connection;
        try {
            connection = new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            response = connection.getInputStream();
        } catch (IOException e) {
            Log.d("com.example.weather2",e.getMessage());
             AppUI.toast("City Not Found");
//            System.out.println("Something went wrong processing URL: " + url+"\nProbably city was not found.\nRemember, the format for city by name is: city,state,country");
//            e.printStackTrace();
//            System.exit(1);
        }

        try (Scanner scanner = new Scanner(response)) {
            return scanner.useDelimiter("\\A").next();
        }
        catch (Exception e){
            Log.e("com.example.weather2","Error at Scanner");
            return null;
        }
    }

    private static String getURL(String base, Map<String,String> params){
        StringBuilder query= new StringBuilder("?");
        for (Map.Entry<String,String> entry : params.entrySet()) {
            try {
                query.append(getParameter(entry.getKey(), entry.getValue()));
            } catch (UnsupportedEncodingException e) {
                System.out.println("Could not encode parameters");
                e.printStackTrace();
                System.exit(1);
            }
            query.append(paramDelimiter);
        }
        return base+query.deleteCharAt(query.length()-1).toString();
    }

    private static String getParameter(String key, String value) throws UnsupportedEncodingException {
        return String.format("%s=%s",URLEncoder.encode(key, charset), URLEncoder.encode(value, charset));
    }

}
