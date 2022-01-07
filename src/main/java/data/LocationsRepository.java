package data;

import data.network.geocoding.GeocodingApi;
import data.network.geocoding.Hit;
import io.reactivex.Single;

import java.util.List;

public class LocationsRepository {

    private final GeocodingApi geocodingApi;

    public LocationsRepository(GeocodingApi geocodingApi) {
        this.geocodingApi = geocodingApi;
    }

    Single<List<Hit>> findPlaces(String query) {
        return geocodingApi.queryPlaces(query, GeocodingApi.API_KEY).map(response -> response.hits);
    }
}
