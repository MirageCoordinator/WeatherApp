package ru.dellirium.weatherapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

public class FragmentMain extends Fragment {
    private static final String KEY = "parcelCities";
    private Parcel currentParcel;

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

        if (savedInstanceState != null)
            currentParcel = (Parcel) savedInstanceState.getSerializable(KEY);
        else
            currentParcel = new Parcel("Moscow", false, false);

        Button button = getView().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox humidityCheckbox = getView().findViewById(R.id.humidityCheckbox);
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

}
