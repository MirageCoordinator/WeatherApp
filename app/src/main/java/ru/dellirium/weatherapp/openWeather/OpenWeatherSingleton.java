package ru.dellirium.weatherapp.openWeather;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenWeatherSingleton {
    private static final String BASE_URL = "http://api.openweathermap.org/";
    private static OpenWeatherSingleton singleton = null;
    private IOpenWeather API;

    private OpenWeatherSingleton() {
        API = createAdapter();
    }

    public static OpenWeatherSingleton getInstance() {
        if(singleton == null) {
            singleton = new OpenWeatherSingleton();
        }

        return singleton;
    }

    public IOpenWeather getAPI() {
        return API;
    }

    private IOpenWeather createAdapter() {
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return adapter.create(IOpenWeather.class);
    }
}

