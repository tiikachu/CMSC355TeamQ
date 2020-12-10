package com.example.pocketgarden.weather;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;

public interface CurrentWeatherCallback {
    @MainThread
    void onCurrentWeather (@Nullable final CurrentWeather currentWeather);

    @MainThread
    void onError (@Nullable Exception exception);
}
