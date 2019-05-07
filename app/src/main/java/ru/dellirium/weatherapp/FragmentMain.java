package ru.dellirium.weatherapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class FragmentMain extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = getView().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {      // Обработка нажатий
            @Override
            public void onClick(View v) {
                CheckBox humidityCheckbox = getView().findViewById(R.id.humidityCheckbox);
                CheckBox cloudinessCheckbox = getView().findViewById(R.id.cloudinessCheckbox);
                Spinner townTextField = getView().findViewById(R.id.editText);
                Intent intent = new Intent(getActivity(), ActivityWeatherShow.class);
                intent.putExtra("TownName", townTextField.getSelectedItem().toString());
                intent.putExtra("humidityCheckbox", humidityCheckbox.isChecked());
                intent.putExtra("cloudinessCheckbox", cloudinessCheckbox.isChecked());
                startActivity(intent);
            }
        });
    }


}
