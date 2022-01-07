import data.LocationsApp;
import data.LocationsRepository;
import data.input.RxInput;
import data.network.geocoding.GeocodingApi;
import data.network.geocoding.GeocodingApiFactory;
import data.network.weather.WeatherApi;
import data.network.weather.WeatherApiFactory;

public class Main {
    public static void main(String[] args) {
        GeocodingApi geocodingApi = GeocodingApiFactory.create();
        WeatherApi weatherApi = WeatherApiFactory.create();
        LocationsRepository repository = new LocationsRepository(geocodingApi, weatherApi);
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
