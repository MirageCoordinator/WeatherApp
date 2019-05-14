package ru.dellirium.weatherapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentWeatherShow extends Fragment {

    public static final String PARCEL = "parcel";

    public static FragmentWeatherShow create (Parcel parcel) {
        FragmentWeatherShow fragment = new FragmentWeatherShow();
        Bundle args = new Bundle();
        args.putSerializable(PARCEL, parcel);
        fragment.setArguments(args);
        return fragment;
    }

    public Parcel getParcel() {
        return (Parcel) getArguments().getSerializable(PARCEL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_weather_show, container, false);

        TextView temperatureText = layout.findViewById(R.id.temperature);
        String temperature = getString(R.string.temperature);
        temperature = String.format(temperature, 5);
        temperatureText.setText(temperature);

        boolean humidityCheckbox = getParcel().isHumidityCheckbox();
        boolean cloudinessCheckbox = getParcel().isCloudinessCheckbox();

        if (humidityCheckbox) {
            TextView humidityText = layout.findViewById(R.id.humidity);
            String humidity = getString(R.string.humidity);
            humidity = String.format(humidity, 79);
            humidityText.setText(humidity);
            humidityText.setVisibility(View.VISIBLE);
        }

        if (cloudinessCheckbox) {
            TextView cloudinessText = layout.findViewById(R.id.cloudiness);
            String cloudiness = getString(R.string.cloudiness);
            cloudiness = String.format(cloudiness, "Высокая");
            cloudinessText.setText(cloudiness);
            cloudinessText.setVisibility(View.VISIBLE);
        }

        TextView townName = layout.findViewById(R.id.town_name);
        townName.setText(getParcel().getCityName());

        return layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
