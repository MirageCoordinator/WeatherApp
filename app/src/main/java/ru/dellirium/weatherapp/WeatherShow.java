package ru.dellirium.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WeatherShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_show);
        Intent intent = getIntent();

        TextView temperatureText = findViewById(R.id.temperature);
        String temperature = getString(R.string.temperature);
        temperature = String.format(temperature, 5);
        temperatureText.setText(temperature);

        boolean humidityCheckbox = intent.getExtras().getBoolean("humidityCheckbox");
        boolean cloudinessCheckbox = intent.getExtras().getBoolean("cloudinessCheckbox");

        if (humidityCheckbox) {
            TextView humidityText = findViewById(R.id.humidity);
            String humidity = getString(R.string.humidity);
            humidity = String.format(humidity, 79);
            humidityText.setText(humidity);
            humidityText.setVisibility(View.VISIBLE);
        }

        if (cloudinessCheckbox) {
            TextView cloudinessText = findViewById(R.id.cloudiness);
            String cloudiness = getString(R.string.cloudiness);
            cloudiness = String.format(cloudiness, "Высокая");
            cloudinessText.setText(cloudiness);
            cloudinessText.setVisibility(View.VISIBLE);
        }

        TextView townName = findViewById(R.id.town_name);
        townName.setText(intent.getStringExtra("TownName"));

    }
}
