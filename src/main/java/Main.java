import data.LocationsApp;
import data.LocationsRepository;
import data.input.RxInput;
import data.network.SampeParallelExeution;
import data.network.geocoding.GeocodingApi;
import data.network.geocoding.GeocodingApiFactory;

public class Main {
    public static void main(String[] args) {
        GeocodingApi geocodingApi = GeocodingApiFactory.create();
        LocationsRepository repository = new LocationsRepository(geocodingApi);
        RxInput input = new RxInput();

        LocationsApp app = new LocationsApp(repository, input);

//        app.execute()
//                .doOnError(Throwable::printStackTrace)
//                .onErrorComplete()
//                .blockingAwait();
//
//        System.exit(0);

        SampeParallelExeution.kok()
                .subscribe(System.out::println);
    }
}
