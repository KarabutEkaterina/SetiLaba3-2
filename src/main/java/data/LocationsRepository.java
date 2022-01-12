package data;

import data.network.geocoding.GeocodingApi;
import data.network.geocoding.Hit;
import data.network.places.PlacesApi;
import data.network.weather.Weather;
import data.network.weather.WeatherApi;
import data.network.places.Place;
import io.reactivex.Single;

import java.util.List;

public class LocationsRepository {

    private final GeocodingApi geocodingApi;

    private final WeatherApi weatherApi;

    private final PlacesApi placesApi; /*mine*/

    public LocationsRepository(GeocodingApi geocodingApi, WeatherApi weatherApi, PlacesApi placesApi) {
        this.geocodingApi = geocodingApi;
        this.weatherApi = weatherApi;
        this.placesApi = placesApi; /*mine*/
    }

    Single<List<Hit>> findPlaces(String query) {
        return geocodingApi.queryPlaces(query, GeocodingApi.API_KEY)
                .map(response -> response.hits);
    }

    Single<Weather> getWeather(Double lat, Double lon){
        return weatherApi.getWeather(lat, lon, WeatherApi.API_KEY)
                .map(response -> response.weather);
    }

    /*mine*/
    Single<List<Place>> getInterestingPlaces(Double lat, Double lon){
        String lang = "en";
        Double radius = 2000.0;
        return placesApi.whatToSee(lang, radius, lon, lat)
                .map(response-> response.place);
    }

}
