import data.LocationsApp;
import data.LocationsRepository;
import data.input.RxInput;
import data.network.geocoding.GeocodingApi;
import data.network.geocoding.GeocodingApiFactory;
import data.network.places.PlacesApi;
import data.network.places.PlacesApiFactory;
import data.network.weather.WeatherApi;
import data.network.weather.WeatherApiFactory;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        GeocodingApi geocodingApi = GeocodingApiFactory.create();
        WeatherApi weatherApi = WeatherApiFactory.create();

            PlacesApi placesApi = PlacesApiFactory.create(); /*mine*/
            LocationsRepository repository = new LocationsRepository(geocodingApi, weatherApi, placesApi);
            RxInput input = new RxInput();

            LocationsApp app = new LocationsApp(repository, input);

            app.execute()
                    .doOnError(Throwable::printStackTrace)
                    .onErrorComplete()
                    .blockingAwait();

            System.exit(0);
//
//        SampeParallelExeution.kok()
//                .subscribe(System.out::println);

 }
}
