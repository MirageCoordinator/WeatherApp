package ru.dellirium.weatherapp;

import java.io.Serializable;

public class Parcel implements Serializable {

private String cityName;
private boolean humidityCheckbox;
private boolean cloudinessCheckbox;

    public Parcel(String cityName, boolean humidityCheckbox, boolean cloudinessCheckbox) {
        this.cityName = cityName;
        this.humidityCheckbox = humidityCheckbox;
        this.cloudinessCheckbox = cloudinessCheckbox;
    }

    public String getCityName() {
        return cityName;
    }

    public boolean isHumidityCheckbox() {
        return humidityCheckbox;
    }

    public boolean isCloudinessCheckbox() {
        return cloudinessCheckbox;
    }
}
