package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;

/**
 * Created by alai on 13/07/2017.
 */
public interface Forecasters {

    public Forecast forecastFor(Region r, Day d);
}
