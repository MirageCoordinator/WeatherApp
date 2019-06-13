package ru.dellirium.weatherapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.dellirium.weatherapp.openWeather.OpenWeatherSingleton;
import ru.dellirium.weatherapp.openWeather.WeatherRequestRestModel;



public class FragmentWeatherShow extends Fragment {
    TextView temperatureText;
    WeatherRequestRestModel model = new WeatherRequestRestModel();

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

        temperatureText = layout.findViewById(R.id.temperature);
        requestRetrofit(getParcel().getCityName());

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

    private void requestRetrofit(String city) {
        OpenWeatherSingleton.getInstance().getAPI().loadWeather(city + ",ru",
                "b6907d289e10d714a6e88b30761fae22", "metric")
                .enqueue(new Callback<WeatherRequestRestModel>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherRequestRestModel> call,
                                           @NonNull Response<WeatherRequestRestModel> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            model = response.body();
                            setTemperature();
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherRequestRestModel> call, Throwable t) {
                        temperatureText.setText(R.string.error);
                    }
                });

    }
    private void setTemperature() {
        String text = getString(R.string.temperature) + ": " + model.main.temp;
        temperatureText.setText(text);
    }
}
