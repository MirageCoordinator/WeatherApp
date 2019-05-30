package ru.dellirium.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class ActivityAbout extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            FragmentAbout details = new FragmentAbout();
            getFragmentManager().beginTransaction()
                    .add(R.id.about_fragment, details)
                    .commit();
        }
    }

    public void goBack(View view) {
        finish();
    }
}
