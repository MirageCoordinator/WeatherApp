package ru.dellirium.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class ActivityWeatherShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_show);

        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            FragmentWeatherShow details = new FragmentWeatherShow();
            details.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction()
                    .add(R.id.weather_show_fragment, details)
                    .commit();
        }
    }
}
