package data;

import data.network.geocoding.GeocodingApi;
import data.network.geocoding.Hit;
import data.network.weather.Weather;
import data.network.weather.WeatherApi;
import io.reactivex.Single;

import java.util.List;

public class LocationsRepository {

    private final GeocodingApi geocodingApi;

    private final WeatherApi weatherApi;

    public LocationsRepository(GeocodingApi geocodingApi, WeatherApi weatherApi) {
        this.geocodingApi = geocodingApi;
        this.weatherApi = weatherApi;
    }

    Single<List<Hit>> findPlaces(String query) {
        return geocodingApi.queryPlaces(query, GeocodingApi.API_KEY)
                .map(response -> response.hits);
    }

    Single<Weather> getWeather(Double lat, Double lon){
        return weatherApi.getWeather(lat, lon, WeatherApi.API_KEY)
                .map(response -> response.weather);
    }
}
