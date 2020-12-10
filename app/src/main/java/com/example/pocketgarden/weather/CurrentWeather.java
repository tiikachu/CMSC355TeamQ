/**
 * CurrentWeather class with location, conditon ID for figuring out icon that matches with weather condition,
 * weather condition, and temperature in Kelvin
 */
package com.example.pocketgarden.weather;

public class CurrentWeather {
    final String location;
    final int conditionId;
    final String weatherCondition;
    final double tempKel;

    public CurrentWeather (final String location, final int conditionId, final String weatherCondition, final double tempKel) {
        this.location = location;
        this.conditionId = conditionId;
        this.weatherCondition = weatherCondition;
        this.tempKel = tempKel;
    }

    // API data value for temperature is Kelvin so convert to Fahrenheit
    public int convertToFahrenheit () {
        return (int) (tempKel * 9/5 - 459.67);
    }


}
