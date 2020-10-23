package com.example.weather2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    public ListView listView;
    public ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppUI.main= new WeakReference<Activity>(this);

    }

    public void onTempClick(View view){
        City c=new City(((EditText) findViewById(R.id.editTextCity)).getText().toString());
        Thread thread=new Thread(c);
        thread.start();
    }

    public void onWeatherClick(View view){
        City c=new City(((EditText) findViewById(R.id.editTextCity)).getText().toString());
        c.fullForecast=true;
        Thread thread=new Thread(c);
        thread.start();
    }
}