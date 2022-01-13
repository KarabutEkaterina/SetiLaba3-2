import data.LocationsApp;
import data.LocationsRepository;
import data.network.geocoding.GeocodingApi;
import data.network.geocoding.GeocodingApiFactory;
import data.network.places.PlacesApi;
import data.network.places.PlacesApiFactory;
import data.network.weather.WeatherApi;
import data.network.weather.WeatherApiFactory;

public class Main {
    public static void main(String[] args) {
        GeocodingApi geocodingApi = GeocodingApiFactory.create();
        WeatherApi weatherApi = WeatherApiFactory.create();
        PlacesApi placesApi = PlacesApiFactory.create(); /*mine*/

        LocationsRepository repository = new LocationsRepository(geocodingApi, weatherApi, placesApi);
        LocationsApp app = new LocationsApp(repository);

        app.exec();

        System.exit(0);
    }
}
