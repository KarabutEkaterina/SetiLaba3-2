package data;

import data.input.RxInput;
import data.network.geocoding.Hit;
import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

import java.util.stream.Collectors;

public class LocationsApp {

    private final LocationsRepository repository;
    private final RxInput input;

    public LocationsApp(LocationsRepository repository, RxInput input) {
        this.repository = repository;
        this.input = input;
    }

    //  1
    // Single<Integer>.map(number -> number.toString()) -- Single<String>
    // Single<T>, map: (T) -> T1
    // Single<T>, flatMap: (T) -> Single<T>
    public Completable execute() {
        return input.readLine("Enter place to get info for: ")
                .flatMap(repository::findPlaces)
                .flatMap(places -> {
                    String placesAsString = places.stream()
                            .map(place -> place.name + "(" + place.osmId + ")")
                            .collect(Collectors.joining("\n", "Found places:\n", "\nEnter the id of place for details: "));

                    return input.readLine(placesAsString)
                            .flatMap(idStr -> {
                                int osmId = Integer.parseInt(idStr);

                                Hit selectedPlace = places.stream()
                                        .filter(place -> place.osmId == osmId)
                                        .findFirst()
                                        .get();

//                                return repository.getWeather(selectedPlace.point.lat, selectedPlace.point.lng).map(weather -> {
//                                    System.out.println("Temperature at place: " + weather.temp);
//
//                                    return repository.getInterestingPlaces(selectedPlace.point.lat, selectedPlace.point.lng).flatMap(interestingPlaces -> {
//                                        String interestingPlacesAsString = interestingPlaces.stream()
//                                                .map(place -> place.name + "(" + place.id + ")")
//                                                .collect(Collectors.joining("\n", "Found places:\n", "\n "));
//
//                                       // System.out.println("i am in this function");
//                                        return input.readLine(placesAsString);
//
//                                    });
//
//                                });

                                return repository.getInterestingPlaces(selectedPlace.point.lat, selectedPlace.point.lng).flatMap(interestingPlaces -> {
                                    String interestingPlacesAsString = interestingPlaces.stream()
                                            .map(place -> place.name + "(" + place.id + ")")
                                            .collect(Collectors.joining("\n", "Found places:\n", "\n "));

                                    // System.out.println("i am in this function");
                                    return input.readLine(placesAsString);

                                });

//                                return repository.getInterestingPlaces(selectedPlace.point.lat, selectedPlace.point.lng).flatMap(interestingPlaces -> {
//                                                    String interestingPlacesAsString = interestingPlaces.stream()
//                                                            .map(place -> place.name + "(" + place.id + ")")
//                                                            .collect(Collectors.joining("\n", "Found places:\n", "\n "));
//
//                                        });


                                // TODO
                                /*
                                С помощью метода [3] ищутся интересные места в локации, далее для каждого найденного места с помощью метода [4] ищутся описания, всё это показывается пользователю в виде списка.
                                 */

                                // choose place -> weather
                                //              | -> get interesting places (list) -> get description for place 1 | -> show to user
                                //                                                 -> get description for place n |

                                        });
                })
                .toCompletable()
                .observeOn(Schedulers.io());
    }
}
