package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

public class CachedForecastTest {

    @Test
    public void checkAdapter(){
        Forecasters f = mock(Forecasters.class);
        when(f.forecastFor(Region.LONDON, Day.MONDAY)).thenReturn(new Forecast("Sunny'ish", 500));
        CachedForecast cf = new CachedForecast(f);

        Forecast londonForecast = cf.getFromForecaster(f, Region.LONDON, Day.MONDAY);

        assertEquals(londonForecast.summary(), "Sunny'ish");
        assertEquals(londonForecast.temperature(), 500);
    }

    @Test
    public void checkAPI(){

        CachedForecast cf = new CachedForecast(mock(Forecasters.class))
        {
            protected Forecast getFromForecaster(Forecasters forecaster, Region city, Day day)
            {
                return new Forecast("Rainny", 501);
            }
        };

        Forecast londonForecast = cf.forecastFor(Region.LONDON, Day.MONDAY);

        assertEquals(londonForecast.summary(), "Rainny");
        assertEquals(londonForecast.temperature(), 501);
    }

}
