package ru.dellirium.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "Запуск приложения");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                CheckBox humidityCheckbox = findViewById(R.id.humidityCheckbox);
                CheckBox cloudinessCheckbox = findViewById(R.id.cloudinessCheckbox);
                EditText townTextField = findViewById(R.id.editText);
                Intent intent = new Intent(this, WeatherShow.class);
                intent.putExtra("TownName", townTextField.getText().toString());
                intent.putExtra("humidityCheckbox", humidityCheckbox.isChecked());
                intent.putExtra("cloudinessCheckbox", cloudinessCheckbox.isChecked());
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
