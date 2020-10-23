package com.example.weather2;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class AppUI{
    public static WeakReference <Activity> main;
    static void toast(final String message){
        final Activity activity=main.get();
        if (activity!=null){
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public static void list(FiveDayForecast forecast){
        final Activity activity=main.get();

        if (activity!=null) {
            final ListView listView=(ListView) activity.findViewById(R.id.listView);
            final ArrayAdapter adapter=new ArrayAdapter(activity,R.layout.weather,R.id.weatherView,forecast.catalog);

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listView.setAdapter(adapter);

                }
            });
        }
    }
}
