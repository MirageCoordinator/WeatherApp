package ru.dellirium.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "Запуск приложения");

        TextView temperatureText = findViewById(R.id.temperature);
        String temperature = getString(R.string.temperature);
        temperature = String.format(temperature, 5);
        temperatureText.setText(temperature);


        TextView humidityText = findViewById(R.id.humidity);
        String humidity = getString(R.string.humidity);
        humidity = String.format(humidity, 79);
        humidityText.setText(humidity);

        TextView cloudinessText = findViewById(R.id.cloudiness);
        String cloudiness = getString(R.string.cloudiness);
        cloudiness = String.format(cloudiness, "Высокая");
        cloudinessText.setText(cloudiness);

    }


}
