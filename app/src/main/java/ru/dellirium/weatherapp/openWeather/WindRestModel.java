package ru.dellirium.weatherapp.openWeather;

import com.google.gson.annotations.SerializedName;

public class WindRestModel {
    @SerializedName("speed") public float speed;
    @SerializedName("deg") public float deg;
}
