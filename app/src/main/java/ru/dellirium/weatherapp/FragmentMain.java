package ru.dellirium.weatherapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;


public class FragmentMain extends Fragment {
    private static final String KEY = "parcelCities";
    private Parcel currentParcel;

    private SensorManager sensorManager;
    TextView weatherNowTemperature;
    TextView weatherNowHumidity;
    private Sensor sensorTemperature;
    private Sensor sensorHumidity;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        drawMainWeatherWindow(savedInstanceState);
        weatherNowTemperature = getActivity().findViewById(R.id.weatherNowTemperature);
        weatherNowHumidity = getActivity().findViewById(R.id.weatherNowHumidity);

        getSensors();
    }

    private void getSensors() {

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensorTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorManager.registerListener(listenerTemperature, sensorTemperature,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorHumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        sensorManager.registerListener(listenerHumidity, sensorHumidity,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void showTemperatureSensor(SensorEvent event){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Temperature now: ").append(event.values[0]).append(" ℃");
        weatherNowTemperature.setText(stringBuilder);
    }

    private void showHumiditySensor(SensorEvent event){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Humidity now: ").append(event.values[0]).append("%");
        weatherNowHumidity.setText(stringBuilder);
    }

    private void drawMainWeatherWindow(Bundle savedInstanceState) {
        if (savedInstanceState != null)
            currentParcel = (Parcel) savedInstanceState.getSerializable(KEY);
        else
            currentParcel = new Parcel("Moscow", false, false);

        Button button = Objects.requireNonNull(getView()).findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox humidityCheckbox = Objects.requireNonNull(getView()).findViewById(R.id.humidityCheckbox);
                CheckBox cloudinessCheckbox = getView().findViewById(R.id.cloudinessCheckbox);
                Spinner townTextField = getView().findViewById(R.id.editText);
                currentParcel = new Parcel(townTextField.getSelectedItem().toString(), humidityCheckbox.isChecked(), cloudinessCheckbox.isChecked());
                showWeather(currentParcel);
            }
        });

        Button about = getView().findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.weather_fragment, new FragmentAbout());
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                } else {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), ActivityAbout.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY, currentParcel);
    }

    private void showWeather (Parcel parcel) {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentWeatherShow fragmentWeatherShow = FragmentWeatherShow.create(parcel);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.weather_fragment, fragmentWeatherShow);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();


        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), ActivityWeatherShow.class);
                intent.putExtra(FragmentWeatherShow.PARCEL, parcel);
            startActivity(intent);
        }
    }

    SensorEventListener listenerTemperature = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}

        @Override
        public void onSensorChanged(SensorEvent event) {
            showTemperatureSensor(event);
        }
    };

    SensorEventListener listenerHumidity = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}

        @Override
        public void onSensorChanged(SensorEvent event) {
            showHumiditySensor(event);
        }
    };
}
