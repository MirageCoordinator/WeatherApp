package ru.dellirium.weatherapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentWeatherShow extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather_show, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent intent = getActivity().getIntent();

        TextView temperatureText = getView().findViewById(R.id.temperature);
        String temperature = getString(R.string.temperature);
        temperature = String.format(temperature, 5);
        temperatureText.setText(temperature);

        boolean humidityCheckbox = intent.getExtras().getBoolean("humidityCheckbox");
        boolean cloudinessCheckbox = intent.getExtras().getBoolean("cloudinessCheckbox");

        if (humidityCheckbox) {
            TextView humidityText = getView().findViewById(R.id.humidity);
            String humidity = getString(R.string.humidity);
            humidity = String.format(humidity, 79);
            humidityText.setText(humidity);
            humidityText.setVisibility(View.VISIBLE);
        }

        if (cloudinessCheckbox) {
            TextView cloudinessText = getView().findViewById(R.id.cloudiness);
            String cloudiness = getString(R.string.cloudiness);
            cloudiness = String.format(cloudiness, "Высокая");
            cloudinessText.setText(cloudiness);
            cloudinessText.setVisibility(View.VISIBLE);
        }

        TextView townName = getView().findViewById(R.id.town_name);
        townName.setText(intent.getStringExtra("TownName"));
    }
}
